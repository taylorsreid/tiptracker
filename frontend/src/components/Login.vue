<template>

    <MDBInput label="Email" size="lg" v-model="email" />

    <MDBInput label="Password" type="password" size="lg" v-model="password" />

    <MDBBtn color="primary" rounded @click="$emit('loginClick', email, password)">Login</MDBBtn>

    <MDBBtn color="secondary" rounded>Register</MDBBtn>

</template>

<script setup lang="ts">
    import { MDBInput } from 'mdb-vue-ui-kit';
    import { MDBBtn } from "mdb-vue-ui-kit";
    import { ref } from 'vue';

    let email:string = ref('').value;
    let password:string = ref('').value;
</script>

<style>

</style>