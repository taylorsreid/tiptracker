<template>
    <div v-if="errorMessage !== ''" id="errorMessage">{{ errorMessage }}</div>
</template>

<script setup lang="ts">
defineProps({
    errorMessage: String
})
</script>

<style>
    
</style>