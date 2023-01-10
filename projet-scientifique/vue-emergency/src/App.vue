<template>
  <div class="content">
    <EmergencyMap
      id="map"
      :centres="centres"
      :urgences="urgences"
      :capteurs="capteurs"
      @update:camions="onUpdateCamions"
    />
    <!-- <RecapRessources
      id="recapRessources"
      :centres="centres"
      :urgences="urgences"
      :camions="camions"
    /> -->
  </div>
</template>

<script>
import EmergencyMap from "./components/EmergencyMap.vue";
import axios from "axios";

export default {
  name: "App",
  components: {
    EmergencyMap,
  },
  data() {
    return {
      centres: [],
      urgences: [],
      camions: [],
      capteurs: []
    };
  },
  async created() {
    /** Chargement asynchrone des données depuis les json */
    await Promise.all([
      this.chargementCentres(), 
      this.chargementUrgences(),
      this.chargementCapteurs()
    ]);
  },
  methods: {
    async chargementCentres() {
      try {
        const response =await axios.get("http://176.191.15.114:8080/UrgenceManager/Centre/GetCenters?OnlyId=false");
        const centres = await response.data;
        this.centres = centres;
      } catch (error) {
        console.error(error)
      }
    },
    async chargementUrgences() {
      try {
        const response = await fetch("./data/urgences.json");
        const urgences = await response.json();
        this.urgences = urgences;
      } catch (error) {
        console.error(error)
      }
    },
    async chargementCapteurs() {
      try {
        const response = await axios.get("http://176.191.15.114:8080/UrgenceManager/Capteur/GetCapteurs?OnlyId=false");
        const capteurs = await response.data;
        console.log(capteurs)
        this.capteurs = capteurs;
      } catch (error) {
        console.error(error)
      }
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
  display: none;
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
