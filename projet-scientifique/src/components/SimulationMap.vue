<template>
  <div id="map" />
</template>

<script>
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import * as turf from "@turf/turf";
import TypeRessource from "./../enums/TypeRessource";

export default {
  data() {
    return {
      /** @type L.map */
      map: null,
      zoom: 14,
      minZoom: 14,
      maxZoom: 18,
      /** @type array */
      centre: [45.7766461124361, 4.884143173956448],
      capteurs: [],
      limitesCarte: [
        // en bas à gauche
        [45.71041155658168, 4.731144701943372],
        // en haut à droite
        [45.79843412950511, 4.948728755139855],
      ],
      /** @type {L.icon}[] */
      icons: {
        blueIcon: L.icon({
          iconUrl:
            "https://raw.githubusercontent.com/pointhi/leaflet-color-marqueurs/master/img/marqueur-icon-2x-blue.png",
          iconSize: [20, 36],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        redIcon: L.icon({
          iconUrl:
            "https://raw.githubusercontent.com/pointhi/leaflet-color-marqueurs/master/img/marqueur-icon-2x-red.png",
          iconSize: [8, 14],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        sensor: L.icon({
          iconUrl: "./images/sensor.png",
          iconSize: [15, 15],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }),
        sensorActivate: L.icon({
          iconUrl: "./images/sensor-activated.png",
          iconSize: [15, 15],
          iconAnchor: [0, 0],
          popupAnchor: [0, 0],
        }), caserne: L.icon({
          iconUrl: "./images/caserne.png",
          iconSize: [25, 25],
        }),
        caserneInactive: L.icon({
          iconUrl: "./images/caserne-inactive.png",
          iconSize: [25, 25],
        }),
      },
      coinsGrille: [
        45.763823823027685, 4.8618173788693015,
        45.79019363295949, 4.907187868645735
      ],
      capteurFeatureCollection: [],
      grid: {},
      capteurActives: [],
      capteursMarqueurs: [],
      sontTousActives: false,
      nbCapteursMinimum: 3,
      marqueursCasernes: []
    };
  },
  props: {
    marqueursFeu: {
      type: Array
    },
    centres: {
      type: Array
    }
  },
  beforeMount() {
    this.grid = turf.pointGrid(this.coinsGrille, 500, {
      units: "meters",
    });
  },
  async mounted() {
    this.initMap();
    this.initCapteurs();

    this.capteurFeatureCollection = turf.featureCollection(this.capteurs);

    //this.findNearestPointOnClick(this.capteurFeatureCollection);
    this.$emit("update:coinsGrille", this.coinsGrille);
  },
  watch: {
    marqueursFeu() {
      console.log(this.marqueursFeu)
      this.marqueursFeu.forEach((marqueur) => {
        console.log(marqueur)
        this.findNearestPoint([marqueur.getLatLng().lat, marqueur.getLatLng().lng])
        marqueur.addTo(this.map)
      })
    },
    centres() {
      this.initCentres();
    }
  },
  methods: {
    /**
     * Méthode d'initiatilisation de la map leaflet
     */
    initMap() {
      const credits =
        /* html */
        `Projet Scientifique
            - CPE Lyon 4IRC - 2022/2023`;
      this.map = L.map("map", {
        preferCanvas: true,
        zoomControl: false,
        minZoom: this.minZoom,
        maxZoom: this.maxZoom,
        maxBounds: L.latLngBounds(
          L.latLng(this.limitesCarte[0]),
          L.latLng(this.limitesCarte[1])
        ),
        zoomAnimation: false,
        fadeAnimation: true,
        marqueurZoomAnimation: false,
        doubleClickZoom: false,
      }).setView(this.centre, this.zoom);
      const lightLayer = L.tileLayer(
        "https://api.mapbox.com/styles/v1/mapbox/streets-v11/tiles/{z}/{x}/{y}?access_token=" +
        process.env.VUE_APP_MAPBOX_ACCESS_TOKEN,
        {
          attribution: credits,
          maxZoom: this.maxZoom,
          maxNativeZoom: 19,
          id: "mapbox/streets-v11",
          tileSize: 512,
          zoomOffset: -1,
          edgeBufferTiles: 2,
          visible: true,
        }
      );
      lightLayer.addTo(this.map);
      L.control
        .scale({
          imperial: false,
          maxWidth: 200,
          metric: false,
          position: "bottomleft",
        })
        .addTo(this.map);
    },
    /**
     * Méthode d'initialisation des capteurs
     */
    initCapteurs() {
      var capteurs = [];

      this.grid.features.forEach((capteur) => {
        capteurs.push(turf.point(capteur.geometry.coordinates));
        const marqueur = L.marker(capteur.geometry.coordinates, {
          icon: this.icons.sensor,
        });

        this.capteursMarqueurs.push(marqueur);
        marqueur.addTo(this.map);
      });
      this.capteurs = capteurs;
    },
    initCentres() {
      /** Création d'une collection de TurfPoint */
      this.centresFeatureCollection = turf.featureCollection([]);
      this.centres.forEach((centre) => {
        const turfPoint = turf.point([centre.latitude, centre.longitude]);
        if (centre.isAvailable && centre.ressource.length !== 0) {
          this.centresFeatureCollection.features.push(turfPoint);
        }
        /** Ressources associées */
        const caserneMarqueur = this.creationMarqueurCaserne(centre);
        /** Ajout du marqueur */
        caserneMarqueur.addTo(this.map);
      });
    },
    creationTooltipCaserne(caserne, nbCamions = 0, nbPompiers = 0) {
      const adresse = caserne.adresse;
      return `<div>
          <h3>${caserne.nom}</h3>
          <p>
            ${adresse.rue}  
          </p>
          <p>${nbCamions} camions disponibles</p>
          <p>${nbPompiers} pompiers disponibles</p>
        </div>
      `;
    },
    /**
     * @param {Object} caserne
     */
    creationMarqueurCaserne(caserne) {
      var marqueur = {}
      if (caserne.isAvailable && caserne.ressource.length !== 0) {
        const ressources = this.compteRessourcesDisponibles(caserne);
        marqueur = L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserne,
        }).bindTooltip(
          this.creationTooltipCaserne(
            caserne,
            ressources.nbCamions,
            ressources.nbPompiers
          )
        );
      } else {
        marqueur = L.marker([caserne.latitude, caserne.longitude], {
          icon: this.icons.caserneInactive,
        }).bindTooltip(this.creationTooltipCaserne(caserne));
      }
      this.marqueursCasernes.push(marqueur);
      console.log(marqueur)
      return marqueur;
    },
    /**
     * @param {Object} caserne
     */
    compteRessourcesDisponibles(caserne) {
      var nbCamions = 0;
      var nbPompiers = 0;
      caserne.ressource.forEach((ressource) => {
        if (ressource.typeRessource.nom === TypeRessource.CAMION_POMPIER) {
          nbCamions += 1;
        }
        nbPompiers = this.compteNombrePersonnesDisponibles(ressource);
      });
      return {
        nbCamions: nbCamions,
        nbPompiers: nbPompiers,
      };
    },
    compteNombrePersonnesDisponibles(caserne) {
      var nbPersonnes = 0;
      caserne.ressourceComposante.forEach((ressource) => {
        if (ressource.isAvailable) {
          nbPersonnes += ressource.personnes.length;
        }
      });
      return nbPersonnes;
    },
    /**
     * Trouve le point le plus proche du click
     */
    findNearestPoint(point) {
      const turfPoint = turf.point([point[0], point[1]]);

      var tempCapteurFeatureCollection = this.capteurFeatureCollection;
      var tempcapteursMarqueurs = this.capteursMarqueurs;

      for (var i = 0; i < this.nbCapteursMinimum; i++) {
        if (tempCapteurFeatureCollection.features.length !== 0) {
          const nearest = turf.nearestPoint(
            turfPoint,
            tempCapteurFeatureCollection
          );

          if (!this.verifieCapteurDejaActif(nearest)) {
            this.capteurActives.push(nearest.geometry);

            const index = this.findIndexOfmarqueur(
              nearest,
              tempCapteurFeatureCollection
            );

            if (index !== -1) {
              const marqueur = tempcapteursMarqueurs[index];

              tempCapteurFeatureCollection.features.splice(index, 1);
              tempcapteursMarqueurs.splice(index, 1);

              this.map.removeLayer(marqueur);

              L.marker(nearest.geometry.coordinates, {
                icon: this.icons.sensorActivate,
              }).addTo(this.map);
            }
          }
        }
      }
    },
    findNearestPointOnClick() {
      this.map.on("click", (e) => {
        const coord = e.latlng;
        const turfPoint = turf.point([coord.lat, coord.lng]);

        var tempCapteurFeatureCollection = this.capteurFeatureCollection;
        var tempcapteursMarqueurs = this.capteursMarqueurs;

        for (var i = 0; i < this.nbCapteursMinimum; i++) {
          if (tempCapteurFeatureCollection.features.length !== 0) {
            const nearest = turf.nearestPoint(
              turfPoint,
              tempCapteurFeatureCollection
            );

            if (!this.verifieCapteurDejaActif(nearest)) {
              this.capteurActives.push(nearest.geometry);

              const index = this.findIndexOfmarqueur(
                nearest,
                tempCapteurFeatureCollection
              );

              if (index !== -1) {
                const marqueur = tempcapteursMarqueurs[index];

                tempCapteurFeatureCollection.features.splice(index, 1);
                tempcapteursMarqueurs.splice(index, 1);

                this.map.removeLayer(marqueur);

                L.marker(nearest.geometry.coordinates, {
                  icon: this.icons.sensorActivate,
                }).addTo(this.map);
              }
            }
          }
        }
      });
    },
    /**
     * Trouve l'index du point le plus proche dans le tableau capteurFeatureCollection
     * @param {turf.point} nearest
     * @param {turf.featureCollection} capteurFeatureCollection
     */
    findIndexOfmarqueur(nearest, tableau) {
      for (var i = 0; i < tableau.features.length; i++) {
        const capteur = tableau.features[i].geometry.coordinates;
        if (
          capteur[0] === nearest.geometry.coordinates[0] &&
          capteur[1] === nearest.geometry.coordinates[1]
        ) {
          return i;
        }
      }
      return -1;
    },
    /**
     * Vérifie si un capteur est déjà actif pour éviter de recréer le marqueur
     * @param {turf.point} nearest
     */
    verifieCapteurDejaActif(nearest) {
      if (this.capteurActives.length != 0) {
        var estActif = false;
        this.capteurActives.forEach((capteur) => {
          if (
            capteur.coordinates[0] === nearest.geometry.coordinates[0] &&
            capteur.coordinates[1] === nearest.geometry.coordinates[1]
          ) {
            estActif = true;
          }
        });

        if (estActif) {
          return true;
        }
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
}

.leaflet-marqueur-icon {
  transform-origin: center center !important;
}
</style>
