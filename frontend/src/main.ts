import 'mdb-vue-ui-kit/css/mdb.min.css';
import './style.css'
import { createApp } from 'vue'
import App from './App.vue'
import axios, { InternalAxiosRequestConfig } from 'axios';
import Cookies from "js-cookie";

axios.defaults.baseURL = "http://localhost/"
axios.defaults.withCredentials = true;
axios.defaults.headers.common.Accept = 'application/json'
axios.interceptors.request.use((config: InternalAxiosRequestConfig) => {
    config.headers['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN');
    return config;
});

createApp(App).mount('#app')
