<template>
    <Layout>
        <div v-for="(value, key) in user">{{ key }} : {{ value }}</div>
    </Layout>
</template>

<script setup lang="ts">
import Layout from '../components/Layout.vue';
import { User } from '../types';

    const user: User = JSON.parse(sessionStorage.getItem('userData') ?? '');
</script>