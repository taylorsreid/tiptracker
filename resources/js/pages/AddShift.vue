<template>
    <Layout>
        <BForm @submit="(e) => {e.preventDefault()}">
        <BFormSelect v-model="shiftData.job_id">
            <BFormSelectOption v-for="job in userData?.jobs" :value="job.id">{{ job.title }}</BFormSelectOption>" />
        </BFormSelect>
    </BForm>
    </Layout>
</template>

<script setup lang="ts">
import Layout from '../components/Layout.vue';
import { Ref, ref } from 'vue';
import { ShiftData, UserData } from '../types';

    const userData:UserData= JSON.parse(sessionStorage.getItem('userData'));

    const shiftData:Ref<ShiftData> = ref(new ShiftData());
</script>