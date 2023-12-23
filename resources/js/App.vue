<template>
    <RouterView @showError="(errorArg:string) => {errorMessage = errorArg}" @hideError="errorMessage = ''" />
    <Error :error-message="errorMessage" v-show="errorMessage !== ''" @hideError="errorMessage = ''" />
</template>

<script setup lang="ts">
    import router from './router';
    import Error from './components/Error.vue';
    import { Ref, ref } from 'vue';

    if (sessionStorage.getItem('userData') === null) {
        router.push('/login')
    }
    else {
        router.push('/')
    }

    let errorMessage:Ref<string> = ref('')
</script>

<style>
</style>