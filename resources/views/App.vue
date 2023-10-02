<template>
    <RouterView />
</template>

<script setup>
    import { useRouter } from 'vue-router' 
    const router = useRouter();
    router.push('/login');
</script>

<style>
</style>