<template>
  <div class="content">
    <div class="map-container">
      <SimulationMap 
        :centres="centres" 
        :capteurs="capteurs"
        :urgences="urgences"  
      />
    </div>
    <Loader :chargement="chargement" />
  </div>
</template>

<script>
import SimulationMap from "./components/SimulationMap.vue";
import Loader from "./components/Loader.vue";
import axios from "axios";

export default {
  name: "App",
  components: {
    SimulationMap,
    Loader,
  },
  data() {
    return {
      capteurs: null,
      urgences: null,
      centres: null,
      adresseIp: "192.168.109.139",
      chargement: true
    };
  },
  async created() {
    await Promise.all([
      this.chargementCentres(),
      this.chargementCapteurs(),
      this.chargementUrgences()
    ]).then(() => {
      this.chargement = false
    });
  },
  methods: {
    async chargementCentres() {
      try {
        const response = await axios.get(
          `http://${this.adresseIp}:9090/UrgenceManager/Centre/GetCenters?OnlyId=false`
        );
        const centres = response.data;
        this.centres = centres;
      } catch (error) {
        console.error(error);
      }
    },
    async chargementCapteurs() {
      try {
        const response = await axios.get(
          `http://${this.adresseIp}:9090/UrgenceManager/Capteur/GetCapteurs?OnlyId=false`
        );
        const capteurs = await response.data;
        this.capteurs = capteurs;
      } catch (error) {
        console.error(error);
      }
    },
    async chargementUrgences() {
      try {
        const response = await axios.get(
          `http://${this.adresseIp}:9090/UrgenceManager/Urgence/GetUrgencies?OnlyId=false`
        );
        const urgences = await response.data;
        this.urgences = urgences;
      } catch (error) {
        console.error(error);
      }
    },
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
  height: 100vh;
  width: 100vw;
}

.map-container {
  width: 100%;
  height: 100%;
  z-index: 1;
}

.menu-container {
  width: 20%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
}

#menu {
  height: 20%;
  width: 100%;
}

#simulation {
  height: 80%;
  width: 100%;
  background-color: lightblue;
}
.leaflet-routing-container {
  visibility: hidden;
  /* Cacher le carré vide de la liste de étapes de l'itinéraire */
}
</style>
