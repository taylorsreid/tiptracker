<template>
    <NavBar></NavBar>
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

    let errorMessage:Ref<string> = ref('')

</script>

<style>

</style>