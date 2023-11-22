<template>
    <router-view @showError="(errorArg:string) => {errorMessage = errorArg}" @hideError="errorMessage = ''"></router-view>
    <Error :error-message="errorMessage" v-show="errorMessage !== ''" @hideError="errorMessage = ''"></Error>
</template>

<script setup lang="ts">
    import { Ref, ref } from 'vue';
    import Error from './components/Error.vue';

    let errorMessage:Ref<string> = ref('')
</script>

<style>
    /* required to offset from the navbar */
    body {
        padding-top: 2%;
    }
</style>