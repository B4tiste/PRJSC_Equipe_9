<template>
  <div class="content">
    <EmergencyMap
      id="map"
      :centres="centres"
      :urgences="urgences"
      :capteurs="capteurs"
      :adresseIp="adresseIp"
      :port="port"
      @update:camions="onUpdateCamions"
    />
    <!-- <RecapRessources
      id="recapRessources"
      :centres="centres"
      :urgences="urgences"
      :camions="camions"
    /> -->
    <Loader :chargement="chargement" />
  </div>
</template>

<script>
import EmergencyMap from "./components/EmergencyMap.vue";
import Loader from "./components/Loader.vue";
import axios from "axios";

export default {
  name: "App",
  components: {
    EmergencyMap,
    Loader
  },
  data() {
    return {
      centres: [],
      urgences: [],
      camions: [],
      capteurs: [],
      adresseIp: "192.168.157.139",
      port: "9090",
      chargement: true
    };
  },
  async created() {
    /** Chargement asynchrone des données depuis les json */
    await Promise.all([
      this.chargementCentres(),
      this.chargementUrgences(),
      this.chargementCapteurs(),
    ]).then(() => {
      this.chargement = false;
    });

  },
  async mounted() {
    setInterval(async () => {
      await Promise.all([
        this.chargementCapteurs(),
        this.chargementUrgences()
      ])
    }, 10000)

  },
  methods: {
    async chargementCentres() {
      try {
        const response = await axios.get(
          `http://${this.adresseIp}:${this.port}/UrgenceManager/Centre/GetCenters?OnlyId=false`
        );
        const centres = await response.data;
        this.centres = centres;
      } catch (error) {
        console.error(error);
      }
    },
    async chargementUrgences() {
      try {
        const response = await axios.get(
          `http://${this.adresseIp}:${this.port}/UrgenceManager/Urgence/GetUrgencies?OnlyId=false`
        );
        const urgences = await response.data;
        this.urgences = urgences;
      } catch (error) {
        console.error(error);
      }
    },
    async chargementCapteurs() {
      try {
        const response = await axios.get(
          `http://${this.adresseIp}:${this.port}/UrgenceManager/Capteur/GetCapteurs?OnlyId=false`
        );
        const capteurs = await response.data;
        this.capteurs = capteurs;
      } catch (error) {
        console.error(error);
      }
    },
    onUpdateCamions(camions) {
      this.camions = camions;
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
  flex-direction: row-reverse;
  height: 100vh;
  width: 100vw;
}

#map {
  width: calc(100% * (3 / 4));
  height: 100%;
  z-index: 10;
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
