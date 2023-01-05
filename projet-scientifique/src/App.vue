<template>
  <div class="content">
    <div class="menu-container">
      <Menu id="menu" />
      <Simulation
        id="simulation"
        :coinsGrille="coinsGrille"
        @update:marqueursFeu="onMarqueursFeuChange"
      />
    </div>
    <div class="map-container">
      <Map 
        :marqueursFeu="marqueursFeu"
        @update:coinsGrille="onCoinsGrilleChange"
      />
    </div>
  </div>
</template>

<script>
import Map from "./components/Map.vue";
import Menu from "./components/Menu.vue";
import Simulation from "./components/Simulation.vue";

export default {
  name: "App",
  components: {
    Map,
    Menu,
    Simulation,
  },
  data() {
    return {
      coinsGrille: null,
      marqueursFeu: null
    };
  },
  methods: {
    onCoinsGrilleChange(coinsGrille) {
      this.coinsGrille = coinsGrille;
    },
    onMarqueursFeuChange(marqueursFeu) {
      this.marqueursFeu = marqueursFeu;
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
