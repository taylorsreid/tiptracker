<template>
    <Layout>
        <div v-for="(value, key) in user">{{ key }} : {{ value }}</div>
    </Layout>
</template>

<script setup lang="ts">
import Layout from '../components/Layout.vue';
import { useAuthStore } from '../stores'
import { storeToRefs } from 'pinia';
const authStore = useAuthStore()
const { user } = storeToRefs(authStore) // use storeToRefs to that when updating user info, you only have to submit user.value
</script>