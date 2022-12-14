<template>
  <div class="container">
    <div id="map"></div>
  </div>
</template>

<script>
import "leaflet/dist/leaflet.css";
import L from "leaflet";

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
    };
  },
  async mounted() {
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
        minZoom: 13,
        maxZoom: 18,
        maxBounds: L.latLngBounds(L.latLng(45.73041155658168, 4.791144701943372), L.latLng(45.79843412950511, 4.948728755139855)),
      }).setView([45.785603052322436, 4.8694003049000285], 12)

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
      const darkLayer = L.tileLayer(
        "https://api.mapbox.com/styles/v1/mapbox/dark-v10/tiles/{z}/{x}/{y}?access_token=" +
          process.env.VUE_APP_MAPBOX_ACCESS_TOKEN,
        {
          attribution: credits,
          maxZoom: 22,
          maxNativeZoom: 19,
          id: "mapbox/streets-v11",
          tileSize: 512,
          zoomOffset: -1,
          edgeBufferTiles: 2,
          visible: false,
        }
      );
      // Check the current theme of the browser
      if (
        window.matchMedia &&
        window.matchMedia("(prefers-color-scheme: dark)").matches
      ) {
        darkLayer.addTo(this.map);
      } else {
        lightLayer.addTo(this.map);
      }

      L.control
        .layers({
          "💡 Light theme": lightLayer,
          "🌑 Dark theme": darkLayer,
        })
        .addTo(this.map);
      L.control
        .scale({
          imperial: false,
          maxWidth: 200,
          metric: false,
          position: "bottomleft",
        })
        .addTo(this.map);

      // L.GridLayer.GridDebug = L.GridLayer.extend({
      //   createTile: function (coords) {
      //     const tile = document.createElement("div");
      //     tile.style.outline = "1px solid green";
      //     tile.style.fontWeight = "bold";
      //     tile.style.fontSize = "14pt";
      //     tile.style.zIndex = "100";
      //     tile.innerHTML = [coords.z, coords.x, coords.y].join("/");
      //     return tile;
      //   },
      // });

      // L.gridLayer.gridDebug = function (opts) {
      //   return new L.GridLayer.GridDebug(opts);
      // };

      // this.map.addLayer(L.gridLayer.gridDebug());
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