<template>
  <div id="map" />
</template>

<script>
import * as turf from "@turf/turf";
import L from "leaflet";
import "leaflet-routing-machine";
import TypeRessource from "./../enums/TypeRessource";

export default {
  data() {
    return {
      /** @type {L.map} */
      map: null,
      /** @type {L.layerGroup} */
      capteursLayer: null,
      /** @type {L.layerGroup} */
      casernesLayer: null,
      /** @type {L.layerGroup} */
      urgencesLayer: null,
      casernesInactivesLayer: null,
      zoom: 15,
      minZoom: 10,
      maxZoom: 19,
      /** @type {Number[]} */
      centre: [45.77698956668263, 4.885078028351652],
      /** @type {Number[][]} */
      limitesCarte: [
        // en bas à gauche
        [45.71041155658168, 4.731144701943372],
        // en haut à droite
        [45.79843412950511, 4.948728755139855],
      ],
      /** @type {L.icon[]} */
      icons: {
        blueIcon: L.icon({
          iconUrl:
            "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-blue.png",
          iconSize: [25, 41],
          iconAnchor: [25, 41],
        }),
        redIcon: L.icon({
          iconUrl:
            "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png",
          iconSize: [8, 14],
        }),
        sensor: L.icon({
          iconUrl: "./images/sensor.png",
          iconSize: [15, 15],
        }),
        sensorActivate: L.icon({
          iconUrl: "./images/sensor-activated.png",
          iconSize: [15, 15],
        }),
        caserne: L.icon({
          iconUrl: "./images/caserne.png",
          iconSize: [25, 25],
        }),
        caserneInactive: L.icon({
          iconUrl: "./images/caserne-inactive.png",
          iconSize: [25, 25],
        }),
        camion: L.icon({
          iconUrl: "./images/camion-de-pompier.png",
          iconSize: [30, 30],
        }),
        camionEau: L.icon({
          iconUrl: "./images/camion-de-pompier-eau.png",
          iconSize: [30, 30],
        }),
        feuPetit: L.icon({
          iconUrl: "./images/feu.png",
          iconSize: [10, 10],
        }),
        feuGrand: L.icon({
          iconUrl: "./images/feu.png",
          iconSize: [50, 50],
        }),
      },
      centresFeatureCollection: [],
      caserneLaPlusProche: null,
      /** @type L.marker[] */
      marqueursFeux: [],
      /** @type L.marker[] */
      marqueursCasernes: [],
      overlay: [],
      layerControlFiltres: L.control.layers(),
      layerControlRecentrer: L.control.layers(),
    };
  },
  props: {
    centres: {
      type: Array,
    },
    urgences: {
      type: Array,
    },
    capteurs: {
      type: Array,
    },
    adresseIp: {
      type: String,
      require: true,
    },
    port: {
      type: String,
      require: true,
    },
  },
  watch: {
    centres() {
      this.initCentres();
    },
    urgences() {
      if (this.urgencesLayer === null) {
        this.initUrgences();
      } else {
        this.updateUrgences();
      }
    },
    capteurs() {
      if (this.capteursLayer === null) {
        this.initCapteurs();
      } else {
        this.updateCapteurs();
      }
    },
  },
  mounted() {
    this.initMap();
    this.initLayerControl();
  },
  methods: {
    initMap() {
      const credits =
        /* html */
        `Projet Scientifique
            - CPE Lyon 4IRC - 2022/2023`;

      this.map = L.map("map", {
        preferCanvas: true,
        zoomControl: false,
        minZoom: this.minZoom,
        maxZoom: this.maxZoom,
        maxBounds: L.latLngBounds(
          L.latLng(this.limitesCarte[0]),
          L.latLng(this.limitesCarte[1])
        ),
        zoomAnimation: false,
        fadeAnimation: true,
        markerZoomAnimation: false,
        doubleClickZoom: false,
      }).setView(this.centre, this.zoom);

      const lightLayer = L.tileLayer(
        "https://api.mapbox.com/styles/v1/mapbox/streets-v11/tiles/{z}/{x}/{y}?access_token=" +
          process.env.VUE_APP_MAPBOX_ACCESS_TOKEN,
        {
          attribution: credits,
          maxZoom: this.maxZoom,
          maxNativeZoom: 19,
          id: "mapbox/streets-v11",
          tileSize: 512,
          zoomOffset: -1,
          edgeBufferTiles: 2,
          visible: true,
        }
      );

      lightLayer.addTo(this.map);

      L.control
        .scale({
          imperial: false,
          maxWidth: 200,
          metric: false,
          position: "bottomleft",
        })
        .addTo(this.map);
    },
    initRoutingLayer() {
      const routingLayer = L.routing.control({
        fitSelectedRoutes: false,
        autoRoute: true,
        routeWhileDragging: false,
        routeDragInterval: 500,
        draggableWaypoints: false,
        waypointMode: "connect",
        useZoomParameter: false,
        showAlternatives: false,
        addWaypoints: false,
        allowUTurn: true,
        lineOptions: {
          styles: [{ color: "#731DD8", opacity: 1, weight: 2 }],
          missingRouteTolerance: 0,
          extendToWaypoints: false,
        },
        altLineOptions: {
          styles: [{ color: "darkgrey", opacity: 1, weight: 6 }],
          missingRouteTolerance: 0,
          extendToWaypoints: false,
        },
        show: true,
        language: "fr",
        suppressDemoServerWarning: true,
      });
      return routingLayer;
    },
    initCentres() {
      /** Création d'une collection de TurfPoint */
      this.centresFeatureCollection = turf.featureCollection([]);

      this.casernesLayer = L.layerGroup().addTo(this.map);
      this.casernesInactivesLayer = L.layerGroup().addTo(this.map);

      this.centres.forEach((centre) => {
        const turfPoint = turf.point([centre.latitude, centre.longitude]);

        /** @type {L.marker} */
        const caserneMarqueur = this.creationMarqueurCaserne(centre);

        if (centre.isAvailable && centre.ressource.length !== 0) {
          this.centresFeatureCollection.features.push(turfPoint);
          this.casernesLayer.addLayer(caserneMarqueur);
        } else {
          this.casernesInactivesLayer.addLayer(caserneMarqueur);
        }
      });
      this.layerControlFiltres.addOverlay(this.casernesLayer, "casernes");
      this.layerControlFiltres.addOverlay(
        this.casernesInactivesLayer,
        "casernes inactives"
      );
    },
    initCapteurs() {
      this.capteursLayer = L.layerGroup().addTo(this.map);
      this.capteurs.forEach((capteur) => {
        const objetCapteur = this.initIconCapteur(capteur);
        if (
          capteur.microcontrolleur.latitude !== null &&
          capteur.microcontrolleur.longitude !== null
        ) {
          const marqueur = L.marker(
            [
              capteur.microcontrolleur.latitude,
              capteur.microcontrolleur.longitude,
            ],
            {
              icon: objetCapteur.icon,
            }
          );
          const cercle = L.circle(marqueur.getLatLng(), 350, {
            color: "#7895B2",
            fillColor: "#7895B2",
            opacity: 0.01,
          });
          // this.capteursLayer.addLayer(objetCapteur.cercleIntensite);
          this.capteursLayer.addLayer(cercle);
          this.capteursLayer.addLayer(marqueur);
        }
      });
      this.layerControlFiltres.addOverlay(this.capteursLayer, "capteurs");
    },
    updateCapteurs() {
      console.log("Mise à jour capteur");
      this.capteurs.forEach((capteur) => {
        this.capteursLayer.eachLayer((layer) => {
          if (capteur.microcontrolleur.etat === 1) {
            /** Microcontrolleur KO */
            this.capteursLayer.removeLayer(layer);
          }
          if (layer instanceof L.Circle) {
            // layer.setStyle({
            //   color: "red",
            //   fillColor: "#f03",
            //   fillOpacity: 0.5,
            //   radius: 0,
            // });
          }
      });
      })
    },
    updateUrgences() {
      
    },
    /**
     * @param {L.Routing.RouteSelectedEvent} e
     */
    onRouteSelectedEvent(e) {
      // TODO faire appels api pour l'envoie des ressources
      /** Envoie des ressources */
      var camion = {
        position: L.marker(e.route.coordinates[0], {
          icon: this.icons.camion,
        }),
      };

      camion.position.addTo(this.map);
      e.route.coordinates.forEach((coord, index) => {
        setTimeout(() => {
          camion.position.setLatLng([coord.lat, coord.lng]);

          if (
            this.estMarqueurSurPosition(
              camion.position,
              e.route.coordinates[e.route.coordinates.length - 1].lat,
              e.route.coordinates[e.route.coordinates.length - 1].lng
            )
          ) {
            camion.position.setIcon(this.icons.camionEau);
          }
        }, 300 * index);
      });
    },
    /**
     * Ajout des urgences sur la map
     */
    async initUrgences() {
      this.urgencesLayer = L.layerGroup().addTo(this.map);

      for (let urgence of this.urgences) {
        const objetFeu = this.initIconFeu(urgence.incident);
        const marqueur = L.marker(
          [urgence.incident.latitude, urgence.incident.longitude],
          {
            icon: objetFeu.icon,
          }
        );

        this.marqueursFeux.push(marqueur);

        this.urgencesLayer.addLayer(objetFeu.cercleFeu);
        this.urgencesLayer.addLayer(marqueur);
        this.trouveCheminLePlusProche(marqueur.getLatLng(), urgence);
        await new Promise((resolve) => {
          setTimeout(() => resolve(), 5000);
        });
      }
      this.layerControlFiltres.addOverlay(this.urgencesLayer, "urgences");
    },
    initLayerControl() {
      this.layerControlFiltres.addTo(this.map);
    },
    initIconFeu(incident) {
      return {
        cercleFeu: L.circle(
          [incident.latitude, incident.longitude],
          incident.radius * 2,
          {
            color: "red",
            fillColor: "red",
            opacity: 0.5,
          }
        ),
        icon: L.icon({
          iconUrl: "./images/feu.png",
          iconSize: [15, 15],
        }),
      };
    },
    initIconCapteur(capteur) {
      if (capteur.capteurDonnees[0].valeur !== 0) {
        return {
          cercleIntensite: L.circle(
            [
              capteur.microcontrolleur.latitude,
              capteur.microcontrolleur.longitude,
            ],
            0,
            {
              color: "#32DE8A",
              fillColor: "#32DE8A",
              opacity: 0.5,
            }
          ),
          icon: L.icon({
            iconUrl: "./images/sensor.png",
            iconSize: [15, 15],
          }),
        };
      } else {
        return {
          cercleIntensite: L.circle(
            [
              capteur.microcontrolleur.latitude,
              capteur.microcontrolleur.longitude,
            ],
            capteur.radius * 2,
            {
              color: "#32DE8A",
              fillColor: "#32DE8A",
              opacity: 0.5,
            }
          ),
          icon: L.icon({
            iconUrl: "./images/sensor-activated.png",
            iconSize: [15, 15],
          }),
        };
      }
    },
    /**
     * @param {Object} caserne
     */
    creationMarqueurCaserne(caserne) {
      var marker = {};
      if (caserne.isAvailable && caserne.ressource.length !== 0) {
        const ressources = this.compteRessourcesDisponibles(caserne);

        marker = L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserne,
        }).bindTooltip(
          this.creationTooltipCaserne(
            caserne,
            ressources.nbCamions,
            ressources.nbPompiers
          )
        );
      } else {
        marker = L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserneInactive,
        }).bindTooltip(this.creationTooltipCaserne(caserne));
      }

      this.marqueursCasernes.push(marker);
      return marker;
    },
    /**
     * @param {Object} caserne
     */
    compteRessourcesDisponibles(caserne) {
      var nbCamions = 0;
      var nbPompiers = 0;
      caserne.ressource.forEach((ressource) => {
        if (ressource.typeRessource.nom === TypeRessource.CAMION_POMPIER) {
          nbCamions += 1;
        }
        nbPompiers = this.compteNombrePersonnesDisponibles(ressource);
      });

      return {
        nbCamions: nbCamions,
        nbPompiers: nbPompiers,
      };
    },
    compteNombrePersonnesDisponibles(caserne) {
      var nbPersonnes = 0;
      caserne.ressourceComposante.forEach((ressource) => {
        if (ressource.isAvailable) {
          nbPersonnes += ressource.personnes.length;
        }
      });
      return nbPersonnes;
    },
    creationTooltipCaserne(caserne, nbCamions = 0, nbPompiers = 0) {
      const adresse = caserne.adresse;
      return `<div>
          <h3>${caserne.nom}</h3>
          <p>
            ${adresse.rue}  
          </p>
          <p>${nbCamions} camions disponibles</p>
          <p>${nbPompiers} pompiers disponibles</p>
        </div>
      `;
    },
    /**
     * @param {Number} min
     * @param {Number} max
     */
    nombreAleatoire(min, max) {
      return Math.round(Math.random() * (max - min)) + min;
    },
    /**
     * @param {Number[]} coordonnes
     */
    trouveCheminLePlusProche(coordonnes, urgence) {
      urgence.ressources[0].vehicules.forEach((vehicule) => {
        try {
          const waypoints = [
            L.latLng(vehicule.centre.latitude, vehicule.centre.longitude),
            L.latLng(coordonnes.lat, coordonnes.lng),
          ];

          const routingLayer = this.initRoutingLayer();

          /** Ecoute de l'évènement "routeselected" */
          routingLayer.on("routeselected", (e) => {
            this.onRouteSelectedEvent(e);
          });

          this.ajouteRouteSurMap(routingLayer, waypoints);
        } catch (error) {
          console.warn(error);
          console.warn("Aucune caserne à disponibilité");
        }
      });
    },
    ajouteRouteSurMap(routingLayer, waypoints) {
      routingLayer.setWaypoints(waypoints);
      routingLayer.route();
      routingLayer.addTo(this.map);
    },
    /**
     * @param {L.marker} marqueur
     * @param {Number} lat
     * @param {Number} lng
     */
    estMarqueurSurPosition(marqueur, lat, lng) {
      return (
        marqueur.getLatLng().lat === lat && marqueur.getLatLng().lng === lng
      );
    },
    evolutionFeu(feu, ressourcePresente) {
      const marker = this.trouveMarkerFeu(feu);
      if (ressourcePresente) {
        feu.setIcon(this.icons.feuPetit);
      } else {
        feu.setIcon(this.icons.feuGrand);
      }
      marker.addTo(this.map);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}

.localisation {
  display: flex;
  position: absolute;
  justify-content: center;
  align-items: center;
  width: 64px;
  height: 64px;
  border-radius: 50px;
  background-color: white;
  z-index: 100;
  cursor: pointer;
  transition: 0.3s background-color;
  right: 15px;
  box-shadow: 0px 0px 8px 0px var(--manatee);
}

.localisation::after {
  visibility: hidden;
  background-color: var(--white-bg);
  color: var(--indigo);
  font-family: var(--font-family-title);
  font-size: 14px;
  font-weight: bold;
  padding: 5px 20px;
}

.localisation {
  bottom: 20px;
}

.localisation::after {
  content: "Recentrer";
  position: absolute;
  top: 32px;
  transform: translateY(-50%);
  right: 70px;
}

.localisation:hover::after {
  visibility: visible;
  transition: 0.3s;
}

.localisation:hover {
  background-color: #e0e0e0;
  transition: 0.3s background-color;
}
</style>
