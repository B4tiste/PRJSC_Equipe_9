import { createApp } from 'vue'
import App from './App.vue'

import L from "leaflet";
import "leaflet/dist/leaflet.css";

import iconRetinaUrl from "../public/images/empty.png";
import iconUrl from "../public/images/empty.png";
import shadowUrl from "../public/images/empty.png";

// Import du CSS de leaflet
import "leaflet/dist/leaflet.css";

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
    iconRetinaUrl,
    iconUrl,
    shadowUrl,
});

createApp(App).mount('#app')