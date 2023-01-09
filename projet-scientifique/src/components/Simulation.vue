<template>
    <div>
        <h2 class="h4">Simulation</h2>
        <div v-if="marqueursFeu.length !== 0">
            <div v-for="(marqueur, index) in marqueursFeu" :key="index">
                <span>Feu {{index + 1}}</span>
                <p>x : {{ marqueur.getLatLng().lat }}</p> 
                <p>y : {{ marqueur.getLatLng().lng }}</p>
            </div>    
        </div>
    </div>
</template>

<script>
//import _ from "lodash";
import L from "leaflet";

export default {
    data() {
        return {
            /** Nombre de décimales de précision */
            precision: 14,
            interval: null,
            icons: {
                feuIcon: L.icon({
                    iconUrl: "./images/feu.png",
                    iconSize: [30, 30],
                }),
                feuIconMoyen: L.icon({
                    iconUrl: "./images/feu.png",
                    iconSize: [60, 60],
                }),
                feuIconGrand: L.icon({
                    iconUrl: "./images/feu.png",
                    iconSize: [90, 90],
                }),
            },
            marqueursFeu: []
        };
    },
    props: {
        coinsGrille: {
            type: Array,
        },
    },
    watch: {
        coinsGrille() {
            this.generationPointAleatoire(this.coinsGrille);
        },
    },
    methods: {
        /**
         * Génère des coordonnées x, y aléatoires dans un carré de coordonnées
         * @param {*} tableau 
         */
        generationPointAleatoire(tableau) {
            const x = this.generationValeurAleatoire(tableau[0], tableau[2]);
            const y = this.generationValeurAleatoire(tableau[1], tableau[3]);
            this.creationMarqueurFeu([x, y])
        },
        /**
         * Génère un nombre aléatoire entre 2 bornes
         * @param {Number} min 
         * @param {Number} max 
         */
        generationValeurAleatoire(min, max) {
            return (Math.random() * (max - min) + min).toFixed(this.precision);
        },
        /**
         * Crée un {L.marker} avec les coordonnées générées
         * @param {Number[]} coordonnees 
         */
        creationMarqueurFeu(coordonnees) {
            if (this.marqueursFeu.length < 3) {
                this.marqueursFeu.push(L.marker(coordonnees, {
                    icon: this.icons.feuIcon,
                }));
                console.log(this.marqueursFeu)
                this.$emit("update:marqueursFeu", this.marqueursFeu);
            } else {
                //this.$emit("update:marqueursFeu", this.marqueursFeu);
                clearInterval(this.interval)
            }
        }
    },
    mounted() {
        this.interval = setInterval(() => {
            this.generationPointAleatoire(this.coinsGrille);
        }, 5000);
    },
    beforeUnmount() {
        clearInterval(this.interval);
    },
};
</script>

<style scoped>

</style>
