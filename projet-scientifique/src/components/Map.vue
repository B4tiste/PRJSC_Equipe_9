<template>
  <div class="container">
    <div id="map"></div>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import * as turf from "@turf/turf";

export default {
  data() {
    return {
      /** @type L.map */
      map: null,
      zoom: 14,
      minZoom: 10,
      maxZoom: 20,
      /** @type array */
      centre: [45.76998015720915, 4.884919481157877],
      scrollWheelZoom: true,
      loading: true,
      options: null,
      capteurs: [],
      bounds: [
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
          iconSize: [8, 14],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        redIcon: L.icon({
          iconUrl:
            "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png",
          iconSize: [8, 14],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        sensor: L.icon({
          iconUrl: "./images/sensor.png",
          iconSize: [15, 15],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        sensorActivated: L.icon({
          iconUrl: "./images/sensor-activated.png",
          iconSize: [15, 15],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
      },
      corners: [
        45.75544092417402, 4.858933317147795, 45.790549102141334,
        4.908539830118779,
      ],
      capteurFeatureCollection: [],
      grid: {},
      capteurActives: [],
      capteursMarkers: []
    };
  },
  beforeMount() {
    this.grid = turf.pointGrid(this.corners, 500, {
      units: "meters",
    });
  },
  mounted() {
    this.initMap();
    this.initCapteurs();

    this.capteurFeatureCollection = turf.featureCollection(this.capteurs);
    this.findNearestPoint(this.capteurFeatureCollection);
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
          L.latLng(this.bounds[0]),
          L.latLng(this.bounds[1])
        ),
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
    /**
     * Méthode d'initialisation des capteurs
     */
    initCapteurs() {
      var capteurs = [];

      this.grid.features.forEach((capteur) => {
        capteurs.push(turf.point(capteur.geometry.coordinates));
        const marker = L.marker(capteur.geometry.coordinates, {
          icon: this.icons.sensor,
        });

        this.capteursMarkers.push(marker);
        marker.addTo(this.map);
      });
      this.capteurs = capteurs;
    },
    /**
     * Trouve le point le plus proche du click
     */
    findNearestPoint() {
      this.map.on("click", (e) => {
        const coord = e.latlng;
        const turfPoint = turf.point([coord.lat, coord.lng]);

        const nearest = turf.nearestPoint(
          turfPoint,
          this.capteurFeatureCollection
        );

        if (!this.verifieCapteurDejaActif(nearest)
        ) {
          this.capteurActives.push(nearest.geometry);

          const index = this.findIndexOfMarker(nearest);

          if (index !== -1) {
            const marker = this.capteursMarkers[index];

            this.map.removeLayer(marker);

            L.marker(nearest.geometry.coordinates, {
              icon: this.icons.sensorActivated,
            }).addTo(this.map);
          }


        }
      });
    },
    /**
     * Trouve l'index du point le plus proche dans le tableau capteurFeatureCollection
     * @param {turf.point} nearest
     * @param {turf.featureCollection} capteurFeatureCollection
     */
    findIndexOfMarker(nearest) {
      for (var i = 0; i < this.capteurFeatureCollection.features.length; i++) {
        const capteur =
          this.capteurFeatureCollection.features[i].geometry.coordinates;
        if (
          capteur[0] === nearest.geometry.coordinates[0] &&
          capteur[1] === nearest.geometry.coordinates[1]
        ) {
          return i;
        }
      }
      return -1;
    },
    /**
     * Vérifie si un capteur est déjà actif pour éviter de recréer le marker
     * @param {turf.point} nearest 
     */
    verifieCapteurDejaActif(nearest) {
      if (this.capteurActives.length != 0) {
        var estActif = false;
        this.capteurActives.forEach((capteur) => {
          if (
            capteur.coordinates[0] === nearest.geometry.coordinates[0] &&
            capteur.coordinates[1] === nearest.geometry.coordinates[1]
          ) {
            estActif = true;
          }
        });

        if (estActif) {
          return true;
        }
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100vw;
}

#map {
  width: 100%;
  height: 100%;
}
</style>
