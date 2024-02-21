<template>
    <RouterView />
    <Modal />
</template>

<script setup lang="ts">
    import router from './router';
    import Modal from './components/Modal.vue';

    if (sessionStorage.getItem('userData') === null) {
        router.push('/login')
    }
    else {
        router.push('/')
    }
</script>

<style>
</style>