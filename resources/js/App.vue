<template>
    <Home v-if="isLoggedIn" @logoutClick="logout"></Home>
    <Login v-else @loginClick="login"></Login>
</template>

<script setup lang="ts">

import { Ref, ref } from 'vue';
import Home from './components/Home.vue';
import Login from './components/Login.vue';
import axios from 'axios';

let isLoggedIn: Ref<boolean> = ref(localStorage.getItem('userData') !== null);
    
async function login(email: string, password: string): Promise<void> {
    axios.get('/sanctum/csrf-cookie')
        .then(() => {
            axios.post('/auth/login', {
                email: email,
                password: password
            })
                .then(() => {
                    axios.get('/user')
                        .then((response) => {
                            localStorage.setItem('userData', JSON.stringify(response.data));
                            isLoggedIn.value = true;
                        })
                        .catch((reason) => {
                            console.error(reason);
                        })
                })
                .catch((reason) => {
                    console.error(reason)
                })
        })
        .catch((reason) => {
            console.error(reason);
        })    
}

function logout(): void {
    axios.post('/auth/logout')
        .then(() => {
            localStorage.clear();
            isLoggedIn.value = false;
        })
}
</script>

<style>
</style>