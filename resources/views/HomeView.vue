<template>
    <RouterLink :to="{ name: 'login' }">Login</RouterLink>
</template>


<script setup>
    import { useRouter } from 'vue-router'
    const router = useRouter();
</script>


<style>
</style>