<template>
    <NavBar :user-data="userData"></NavBar>
    <Error :error-message="errorMessage"></Error>
</template>

<script setup lang="ts">

    // if user is not logged in, redirect to login
    if (sessionStorage.getItem('userData') === null) {
        router.push('/')
    }

    import { Ref, ref } from 'vue';
    import router from '../router';
    import NavBar from './NavBar.vue';
    import Error from './Error.vue';

    let errorMessage:Ref<string> = ref('')

    let stringData:string | null = sessionStorage.getItem('userData')
    let userData:object;
    if (stringData !== null) {
        userData = JSON.parse(stringData)
    }

</script>

<style>

</style>