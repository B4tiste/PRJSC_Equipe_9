<template>
  <div class="content">
    <div class="menu-container">
      <RecapSimulation id="menu" />
      <Simulation
        id="simulation"
        :coinsGrille="coinsGrille"
        @update:marqueursFeu="onMarqueursFeuChange"
      />
    </div>
    <div class="map-container">
      <SimulationMap
        :centres="centres"
        :marqueursFeu="marqueursFeu"
        @update:coinsGrille="onCoinsGrilleChange"
      />
    </div>
  </div>
</template>

<script>
import SimulationMap from "./components/SimulationMap.vue";
import RecapSimulation from "./components/RecapSimulation.vue";
import Simulation from "./components/Simulation.vue";

export default {
  name: "App",
  components: {
    SimulationMap,
    RecapSimulation,
    Simulation,
  },
  data() {
    return {
      coinsGrille: null,
      marqueursFeu: null,
      centres: null
    };
  },
  async created() {
    await Promise.all([this.chargementCentres()])
  },
  methods: {
    async chargementCentres() {
      try {
        const response = await fetch("./data/centres.json");
        const centres = await response.json();
        this.centres = centres;
      } catch (error) {
        console.error(error);
      }
    },
    onCoinsGrilleChange(coinsGrille) {
      this.coinsGrille = coinsGrille;
    },
    onMarqueursFeuChange(marqueursFeu) {
      this.marqueursFeu = [...marqueursFeu];
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
  height: 100vh;
  width: 100vw;
}

.map-container {
  width: 80%;
  height: 100%;
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
</style>
