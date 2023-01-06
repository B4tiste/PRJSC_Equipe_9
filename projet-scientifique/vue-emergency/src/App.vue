<template>
  <div class="content">
    <Map />
  </div>
</template>

<script>
import Map from "./components/Map.vue"

export default {
  name: 'App',
  components: {
    Map
  }
}
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

.leaflet-routing-container {
    visibility: hidden; /* Cacher le carré vide de la liste de étapes de l'itinéraire */
}
</style>
