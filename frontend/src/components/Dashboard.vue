<template>
    <NavBar :user-data="userData"></NavBar>
</template>

<script setup lang="ts">

    // if user is not logged in, redirect to login
    if (sessionStorage.getItem('userData') === null) {
        router.push('/')
    }

    import router from '../router';
    import NavBar from './NavBar.vue';

    let stringData:string | null = sessionStorage.getItem('userData')
    let userData:object;
    if (stringData !== null) {
        userData = JSON.parse(stringData)
    }

</script>

<style>

</style>