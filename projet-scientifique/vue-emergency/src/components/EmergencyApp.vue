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
      /** @type L.map */
      map: null,
      zoom: 14,
      minZoom: 14,
      maxZoom: 18,
      /** @type array */
      centre: [45.7766461124361, 4.884143173956448],
      limitesCarte: [
        // en bas à gauche
        [45.71041155658168, 4.731144701943372],
        // en haut à droite
        [45.79843412950511, 4.948728755139855],
      ],
      /** @type {L.icon}[] */
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
      coinsGrille: [
        45.763823823027685, 4.8618173788693015, 45.79019363295949,
        4.907187868645735,
      ],
      centresFeatureCollection: [],
      caserneLaPlusProche: null,
      marqueursFeux: [],
      marqueursCasernes: []
    };
  },
  props: {
    centres: {
      type: Array,
    },
    urgences: {
      type: Array,
    },
  },
  watch: {
    centres() {
      this.initCentres();
    },
    urgences() {
      this.initUrgences();
    },
  },
  mounted() {
    this.initMap();
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
        showAlternatives: true,
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
     * @param {L.Routing.RouteSelectedEvent} e
     */
    onRouteSelectedEvent(e, caserne) {
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
        }, 150 * index);
      });
      console.log(caserne);
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
    /**
     * Ajout des urgences sur la map
     */
    initUrgences() {
      this.urgences.forEach((urgence) => {
        const marqueur = L.marker(
          [urgence.incident.latitude, urgence.incident.longitude],
          {
            icon: this.initIconFeu(),
          }
        );

        this.marqueursFeux.push(marqueur);

        /** Recherche du chemin vers le centre la plus proche */
        marqueur.on("click", () => {
          this.trouveCheminLePlusProche(marqueur.getLatLng());
        });
        marqueur.addTo(this.map);
      });
    },
    initIconFeu() {
      const tailleFeu = this.nombreAleatoire(20, 50);

      return L.icon({
        iconUrl: "./images/feu.png",
        iconSize: [tailleFeu, tailleFeu],
      });
    },
    /**
     * @param {Object} caserne
     */
    creationMarqueurCaserne(caserne) {
      var marker = {}
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
        marker =  L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserneInactive,
        }).bindTooltip(this.creationTooltipCaserne(caserne));
      }

      this.marqueursCasernes.push(marker);
      return marker;
    },
    trouveMarqueurCaserne(coordonnes) {
      console.log(coordonnes)
      this.marqueursCasernes.forEach((marqueur) => {
        console.log(marqueur)
        if (this.estMarqueurSurPosition(marqueur, coordonnes.coordinates[0], coordonnes.coordinates[1])) {
          return marqueur;
        }
      })
    },
    updateMarqueurCaserne() {

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
    trouveCheminLePlusProche(coordonnes) {
      // TODO gérer les récupérations de données par rapport à l'id de la caserne
      const turfPoint = turf.point([coordonnes.lat, coordonnes.lng]);
      try {
        const caserneLaPlusProche = turf.nearestPoint(
          turfPoint,
          this.centresFeatureCollection
        )

        const waypoints = [
          L.latLng(
            caserneLaPlusProche.geometry.coordinates[0],
            caserneLaPlusProche.geometry.coordinates[1]
          ),
          L.latLng(coordonnes.lat, coordonnes.lng),
        ];

        const routingLayer = this.initRoutingLayer();
        
        /** Ecoute de l'évènement "routeselected" */
        routingLayer.on("routeselected", (e) => {
          this.onRouteSelectedEvent(e, coordonnes);
        });
        console.log(caserneLaPlusProche)

        /** Gestion des ressources */
        const caserneMarqueur = this.trouveMarqueurCaserne(caserneLaPlusProche.geometry)
        console.log(caserneMarqueur)
        this.ajouteRouteSurMap(routingLayer, waypoints);
      } catch (error) {
        console.warn(error);
        console.warn("Aucune caserne à disponibilité");
      }
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
        marqueur.getLatLng().lat === lat &&
        marqueur.getLatLng().lng === lng
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
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}
</style>
