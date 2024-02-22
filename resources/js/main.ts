import './style.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import { createPinia } from "pinia";
import piniaPluginPersistedState from "pinia-plugin-persistedstate";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'

createApp(App)
.use(router)
.use(createPinia().use(piniaPluginPersistedState))
.mount('#app')