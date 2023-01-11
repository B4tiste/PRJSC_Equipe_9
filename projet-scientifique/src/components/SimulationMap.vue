<template>
  <div id="map" />
</template>

<script>
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import "leaflet-routing-machine";
import * as turf from "@turf/turf";
import TypeRessource from "./../enums/TypeRessource";

export default {
  data() {
    return {
      /** @type L.map */
      map: null,
      /** @type {L.layerGroup} */
      capteursLayer: null,
      /** @type {L.layerGroup} */
      casernesLayer: null,
      /** @type {L.layerGroup} */
      urgencesLayer: null,
      zoom: 14,
      minZoom: 14,
      maxZoom: 18,
      /** @type array */
      centre: [45.77698956668263, 4.885078028351652],
      limitesCarte: [
        // en bas à gauche
        [45.71041155658168, 4.731144701943372],
        // en haut à droite
        [45.79843412950511, 4.948728755139855],
      ],
      /** @type {L.icon}[] */
      icons: {
        sensor: L.icon({
          iconUrl: "./images/sensor.png",
          iconSize: [15, 15],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        sensorActivate: L.icon({
          iconUrl: "./images/sensor-activated.png",
          iconSize: [15, 15],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
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
      },
      coinsGrille: [
        45.763823823027685, 4.8618173788693015, 45.79019363295949,
        4.907187868645735,
      ],
      capteurFeatureCollection: [],
      capteurActives: [],
      capteursMarqueurs: [],
      nbCapteursMinimum: 5,
      marqueursCasernes: [],
      incidentsPrisEnCompte: [],
      overlay: [],
      layerControlFiltres: L.control.layers(),
      marqueursFeux: [],
      routingLayers: [],
    };
  },
  props: {
    centres: {
      type: Array,
    },
    capteurs: {
      type: Array,
    },
    urgences: {
      type: Array,
    },
  },
  mounted() {
    this.initMap();
    this.initLayerControl();

    setInterval(() => {
      console.log("évolution du feu");
      // Appel api pour l'état des feux

      this.updateUrgences();
    }, 5000);
  },
  watch: {
    centres() {
      this.initCentres();
    },
    capteurs() {
      this.initCapteurs();
    },
    urgences() {
      this.initUrgences();
    },
  },
  methods: {
    /**
     * Méthode d'initiatilisation de la map leaflet
     */
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
        marqueurZoomAnimation: false,
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
    initLayerControl() {
      this.layerControlFiltres.addTo(this.map);
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
          styles: [{ color: "red", opacity: 1, weight: 2 }],
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
    /**
     * Méthode d'initialisation des capteurs
     */
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
            opacity: 0.05,
          });
          this.capteursLayer.addLayer(objetCapteur.cercleIntensite);
          this.capteursLayer.addLayer(cercle);
          this.capteursLayer.addLayer(marqueur);
        }
      });
      this.layerControlFiltres.addOverlay(this.capteursLayer, "capteurs");
    },
    initCentres() {
      /** Création d'une collection de TurfPoint */
      this.centresFeatureCollection = turf.featureCollection([]);
      this.centres.forEach((centre) => {
        const turfPoint = turf.point([centre.latitude, centre.longitude]);
        if (centre.isAvailable && centre.ressource.length !== 0) {
          this.centresFeatureCollection.features.push(turfPoint);
        }
        /** Ressources associées */
        const caserneMarqueur = this.creationMarqueurCaserne(centre);
        /** Ajout du marqueur */
        caserneMarqueur.addTo(this.map);
      });
    },
    initUrgences() {
      this.urgencesLayer = L.layerGroup().addTo(this.map);
      this.urgences.forEach((urgence) => {
        const objetFeu = this.initIconFeu(urgence.incident);
        const marqueur = L.marker(
          [urgence.incident.latitude, urgence.incident.longitude],
          {
            icon: objetFeu.icon,
          }
        );
        this.marqueursFeux.push(marqueur);

        if (!this.feuPrisEnCompte(marqueur)) {
          this.incidentsPrisEnCompte.push(marqueur);

          this.trouveCheminLePlusProche(marqueur.getLatLng(), urgence);
        }

        this.urgencesLayer.addLayer(objetFeu.cercleFeu);
        this.urgencesLayer.addLayer(marqueur);
      });
      this.layerControlFiltres.addOverlay(this.urgencesLayer, "urgences");
    },
    initIconFeu(incident) {
      return {
        cercleFeu: L.circle(
          [incident.latitude, incident.longitude],
          incident.radius * 2000,
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
      var marqueur = {};
      if (caserne.isAvailable && caserne.ressource.length !== 0) {
        marqueur = L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserne,
        });
      } else {
        marqueur = L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserneInactive,
        });
      }
      this.marqueursCasernes.push(marqueur);
      return marqueur;
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
    feuPrisEnCompte(marqueur) {
      if (this.incidentsPrisEnCompte.length === 0) {
        return false;
      }

      return this.incidentsPrisEnCompte.forEach((incident) => {
        if (
          incident.getLatLng().lat === marqueur.getLatLng().lat &&
          incident.getLatLng().lng === marqueur.getLatLng().lng
        ) {
          return true;
        }
      });
    },
    /**
     * @param {Number[]} coordonnes
     */
    trouveCheminLePlusProche(coordonnes, urgence) {
      urgence.ressources.forEach((vehicule, index) => {
        setTimeout(() => {
          try {
            const waypoints = [
              L.latLng(vehicule.centre.latitude, vehicule.centre.longitude),
              L.latLng(coordonnes.lat, coordonnes.lng),
            ];
            const routingLayer = this.initRoutingLayer();
            /** Ecoute de l'évènement "routeselected" */
            routingLayer.on("routeselected", (e) => {
              this.onRouteSelectedEvent(e, routingLayer);
            });
            this.routingLayers.push({
              id: urgence.incidentId,
              routingLayer: routingLayer,
            });
            this.ajouteRouteSurMap(routingLayer, waypoints);
          } catch (error) {
            console.warn(error);
            console.warn("Aucune caserne à disponibilité");
          }
        }, 1000 * index);
      });
    },
    trouveMarqueurCaserne(caserne) {
      var marqueur = {};
      this.marqueursCasernes.forEach((marqueurCaserne) => {
        if (
          caserne.latitude === marqueurCaserne.getLatLng().lat &&
          caserne.longitude === marqueurCaserne.getLatLng().lng
        ) {
          marqueur = marqueurCaserne;
        }
      });
      return marqueur;
    },
    miseAJourMarqueurCaserne(marqueur, centre) {
      // On supprime l'ancien marqueur
      this.map.removeLayer(marqueur);

      // On le recrée
      const nouveauMarqueur = this.creationMarqueurCaserne(centre);
      nouveauMarqueur.addTo(this.map);
    },
    ajouteRouteSurMap(routingLayer, waypoints) {
      routingLayer.setWaypoints(waypoints);
      routingLayer.route();
      routingLayer.addTo(this.map);
    },
    /**
     * @param {L.Routing.RouteSelectedEvent} e
     */
    onRouteSelectedEvent(e, routingLayer) {
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
          console.log("Mise à jour coordonées", camion)
          if (
            this.estMarqueurSurPosition(
              camion.position,
              e.route.coordinates[e.route.coordinates.length - 1].lat,
              e.route.coordinates[e.route.coordinates.length - 1].lng
            )
          ) {
            camion.position.setIcon(this.icons.camionEau);
            this.map.removeControl(routingLayer);
          }
        }, 300 * index);
      });
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
    updateUrgences() {
      console.log(this.urgencesLayer)
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}

.leaflet-marqueur-icon {
  transform-origin: center center !important;
}
</style>
