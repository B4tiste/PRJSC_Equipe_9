<template>
    <div class="ressources">
        <ul v-if="urgences.length !== 0" class="feux-container">
            <div class="feux-title">
                <img src="../../public/images/feu.png" alt="feu" style="height: 50px; width: 50px"> 
                <span>{{feux.length}}</span>
            </div>
            <div>
                <h2 v-if="feux.length === 1">Position du feu</h2>
                <h2 v-else>Position des feux</h2>
            </div>
            <li v-for="(feu, index) in feux" v-bind:key="index" class="feu-position">
                {{ feu.latitude }}
                {{ feu.longitude }}
            </li>
        </ul>
        <ul v-if="centres.length !== 0" class="centres-container">
            <div>
                <h2 v-if="centres.length === 1">La caserne</h2>
                <h2 v-else>Les centres</h2>
            </div>
            <li v-for="(caserne, index) in centres" v-bind:key="index">
                {{ caserne.nom }}
            </li>
        </ul>
        <ul v-if="camions.length !== 0" class="ressources-container">

        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {

        }
    },
    props: {
        centres: {
            type: Array
        },
        urgences: {
            type: Array
        },
        camions: {
            type: Array
        }
    },
    mounted() {
    }
}
</script>

<style scoped>
.ressources {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin: 10px;
    height: 90%;
    width: 100%;
}

.feux-container,
.centres-container,
.ressources-container {
    width: 100%;
    height: calc(100% / 3);
}

.feux-title {
    display: flex;
    justify-content: space-around;
    align-items: center;
}

.feu-position {
    margin: 3px;
    text-decoration: none;
    list-style-type: none;
}
</style>