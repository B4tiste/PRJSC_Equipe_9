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
      zoom: 10,
      centre: [45.76468728013713, 4.8397715955513725],
      scrollWheelZoom: true,
      loading: true,
      options: null,
      bounds: [
        // en bas à gauche
        [45.73041155658168, 4.791144701943372],
        // en haut à droite
        [45.79843412950511, 4.948728755139855],
      ],
    };
  },
  async mounted() {
    this.initMap();

    const grid = turf.pointGrid(
      [
        45.73041155658168, 4.791144701943372, 45.79843412950511,
        4.948728755139855,
      ],
      500,
      {
        units: "meters",
      }
    );
    console.log(grid);
    var icon = L.icon({
      iconUrl: "https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-blue.png",
      iconSize: [8, 14],
      iconAnchor: [0, 0],
      popupAnchor: [0, 0],
    });

    const map = this.map;
    const capteurs = []
    grid.features.forEach(function (marker) {
      capteurs.push(turf.point(marker.geometry.coordinates))
      L.marker(marker.geometry.coordinates, { icon: icon }).addTo(map);
    });

    const capteurFeatureCollection = turf.featureCollection(capteurs)

    this.map.on("click", function (e) {
      var coord = e.latlng;
      var lat = coord.lat;
      var lng = coord.lng;
      const turfPoint = turf.point([lat, lng])
      console.log(turfPoint, capteurFeatureCollection)
      var nearest = turf.nearestPoint(turfPoint, capteurFeatureCollection);
      console.log(nearest)
    });
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
        minZoom: 14,
        maxZoom: 20,
        maxBounds: L.latLngBounds(
          L.latLng(this.bounds[0]),
          L.latLng(this.bounds[1])
        ),
      }).setView([45.785603052322436, 4.8694003049000285], 14);

      const lightLayer = L.tileLayer(
        "https://api.mapbox.com/styles/v1/mapbox/streets-v11/tiles/{z}/{x}/{y}?access_token=" +
          process.env.VUE_APP_MAPBOX_ACCESS_TOKEN,
        {
          attribution: credits,
          maxZoom: 22,
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
  width: 90%;
  height: 90%;
}
</style>
