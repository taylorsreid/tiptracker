<template>

    <div class="centerContainer">
        <h2>Welcome to TipTracker</h2>

        <!-- always shows -->
        <MDBInput label="Email" size="lg" v-model="userData.email" required />
        <MDBInput label="Password" type="password" size="lg" v-model="userData.password" required/>

        <!-- shows only if in register mode -->
        <MDBInput v-if="registerMode" label="Confirm Password" type="password" size="lg" v-model="userData.password_confirmation" />
        <MDBInput v-if="registerMode" label="First Name" size="lg" v-model="userData.first_name"  />
        <MDBInput v-if="registerMode" label="Last Name" size="lg" v-model="userData.last_name" />

        <!-- shows only if in login mode -->
        <MDBBtn v-if="!registerMode" color="primary" rounded @click="login">Login</MDBBtn>
        <MDBBtn v-if="!registerMode" color="secondary" rounded @click="registerMode = true; errorMessage = ''">Register</MDBBtn>

        <!-- shows only if in register mode -->
        <MDBBtn v-if="registerMode" color="primary" rounded @click="register">Submit</MDBBtn>
        <MDBBtn v-if="registerMode" color="secondary" rounded  @click="registerMode = false">Return to Login</MDBBtn>  
    </div>

    <Error :error-message="errorMessage"></Error>

</template>

<script setup lang="ts">

    // if user is already logged in, redirect to dashboard
    if (sessionStorage.getItem('userData') !== null) {
        router.push('/dashboard')
    }

    import { MDBBtn, MDBInput } from "mdb-vue-ui-kit";
    import { Ref, ref } from 'vue';
    import api from '../api';
    import router from '../router';
    import Error from './Error.vue';

    // reactive object linked to form above
    let userData:any = ref({
        email: '',
        password: '',
        password_confirmation: '',
        first_name: '',
        last_name: ''
    }).value 
    let errorMessage:Ref<string> = ref('')
    let registerMode:Ref<Boolean> = ref(false) // reactive toggleable boolean whether or not to show registration fields

    async function login() {
        if (userData.email !== '' && userData.password !== '') {
            try {
                await api.csrf()
                await api.login(userData.email, userData.password) // only send email and password, object may contain registration data as well
                sessionStorage.setItem('userData', JSON.stringify(await api.getUser()))
                router.push('/dashboard')
            } catch (error:any) {
                errorMessage.value = error.response.data.message
            }
        }
        else {
            errorMessage.value = 'Email and password are required.'
        }
    }

    async function register() {

        // check that all required fields are filled out
        let requiredFields:boolean = true;
        for (let [_key, value] of Object.entries(userData)) {
            if (value === '') {
                requiredFields = false
                errorMessage.value = 'All fields are required.'
            }
        }

        // if they are then proceed sending request to api
        if (requiredFields) {
            try {
                await api.csrf()
                await api.register(userData)
                sessionStorage.setItem('userData', JSON.stringify(await api.getUser()))
                router.push('/dashboard')
            } catch (error:any) {
                errorMessage.value = error.response.data.message
            } 
        }
  
    }

</script>

<style>
    .centerContainer {
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
</style>