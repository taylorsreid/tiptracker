<template>
    <NavBar></NavBar>
    <RouterView @loadComponent="(componentName:string) => router.push(`/${componentName}`)"></RouterView>
</template>

<script setup lang="ts">

    // if user is not logged in, redirect to login
    if (sessionStorage.getItem('userData') === null) {
        router.push('/login')
    }
    else {
        router.push('/home')
    }

    import router from '../router';
    import NavBar from './NavBar.vue';

    const emit = defineEmits(['showError'])

</script>

<style>

</style>