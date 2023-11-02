<template>
    <Home v-if="isLoggedIn" @logoutClick="logout"></Home>
    <Login v-else @loginClick="login"></Login>
</template>

<script setup lang="ts">
    import { Ref, ref } from 'vue';
    import Home from './components/Home.vue';
    import Login from './components/Login.vue';
    import axios, { InternalAxiosRequestConfig } from 'axios';
    import apiUri from './apiUri';
    import Cookies from "js-cookie";

    axios.defaults.withCredentials = true;
    axios.defaults.headers.common.Accept = 'application/json'

    axios.interceptors.request.use((config : InternalAxiosRequestConfig) => {
        config.headers['X-XSRF-TOKEN'] = Cookies.get('XSRF-TOKEN');
        return config;
    });

    let isLoggedIn: Ref<boolean> = ref(localStorage.getItem('userData') !== null);

    async function login(email: string, password: string): Promise<void> {
        axios.get(apiUri + 'sanctum/csrf-cookie')
            .then(() => {
                axios.post(apiUri + 'auth/login', {
                    email: email,
                    password: password
                })
                    .then(() => {
                        axios.get(apiUri + 'user')
                            .then((response) => {
                                localStorage.setItem('userData', JSON.stringify(response.data));
                                isLoggedIn.value = true;
                            })
                            .catch(() => {})
                    })
                    .catch(() => {})
            })
            .catch(() => {})

    }

    function logout(): void {
        axios.post(apiUri + 'auth/logout')
            .then(() => {
                localStorage.clear();
                isLoggedIn.value = false;
            })
    }
</script>

<style></style>