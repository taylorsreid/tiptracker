<template>
    <div v-if="errorMessage !== ''" id="errorMessage">{{ errorMessage }}</div>
</template>

<script setup lang="ts">
defineProps({
    errorMessage: String
})
</script>

<style>
    #errorMessage {
        bottom: 5%;
        right: 5%;
        height: fit-content;
        width: fit-content;
        position: fixed;
        background-color: red;
        border-radius: .5rem;
        padding-top: 2.5%;
        padding-bottom: 2.5%;
        padding-left: 5%;
        padding-right: 5%;
        color: black;
        
    }
</style>