<template>
  <div class="content">
    <Map
      id="map"
      @update:casernes="onUpdateCasernes"
      @update:feux="onUpdateFeux"
      @update:camions="onUpdateCamions"
    />
    <RecapRessources
      id="recapRessources"
      :casernes="casernes"
      :feux="feux"
      :camions="camions"
    />
  </div>
</template>

<script>
import Map from "./components/Map.vue";
import RecapRessources from "./components/RecapRessources.vue";

export default {
  name: "App",
  components: {
    Map,
    RecapRessources,
  },
  data() {
    return {
      casernes: [],
      feux: [],
      camions: [],
    };
  },
  methods: {
    onUpdateCasernes(casernes) {
      this.casernes = casernes;
    },
    onUpdateFeux(feux) {
      this.feux = feux;
    },
    onUpdateCamions(camions) {
      this.camions = camions;
    }
  },
};
</script>

<style>
*,
*::after,
*::before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.content {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  flex-direction: row-reverse;
  height: 100vh;
  width: 100vw;
}

#map {
  width: calc(100% * (3 / 4));
  height: 100%;
}

#recapRessources {
  display: flex;
  justify-content: center;
  align-items: center;
  width: calc(100% * (1 / 4));
  height: 100%;
}

.leaflet-routing-container {
  visibility: hidden;
  /* Cacher le carré vide de la liste de étapes de l'itinéraire */
}
</style>
