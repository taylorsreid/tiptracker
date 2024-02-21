<template>

    <div class="centerContainer">
        <h2>Welcome to TipTracker</h2>

        <BForm @submit="(e) => {e.preventDefault()}">
            
            <!-- always shows -->
            <BFormInput
                id="email-input"
                v-model="registrationData.email"
                type="email"
                placeholder="Email"
                required
            />
            <BFormInput
                id="password-input"
                v-model="registrationData.password"
                type="password"
                placeholder="Password"
                required
            />

            <!-- shows only if in register mode -->
            <BFormInput
                id="password-confirmation-input"
                v-if="registerMode"
                v-model="registrationData.password_confirmation"
                type="password"
                placeholder="Confirm Password"
            />
            <BFormInput
                id="first-name-input"
                v-if="registerMode"
                v-model="registrationData.first_name"
                placeholder="First Name"
            />
            <BFormInput
                id="last-name-input"
                v-if="registerMode"
                v-model="registrationData.last_name"
                placeholder="Last Name"
            />

            <!-- shows only if in login mode -->
            <BButton v-if="!registerMode" type="submit" variant="primary" @click="login">Login</BButton>
            <BButton v-if="!registerMode" variant="secondary" @click="registerMode = true">Register</BButton>

            <!-- shows only if in register mode -->
            <BButton v-if="registerMode" type="submit" variant="primary" @click="register">Submit</BButton>
            <BButton v-if="registerMode" type="submit" variant="secondary" @click="registerMode = false">Return to Login</BButton>

        </BForm>
    </div>

</template>

<script setup lang="ts">

    // if user is already logged in, redirect to dashboard
    if (sessionStorage.getItem('userData') !== null) {
        router.push('/')
    }

    import { BForm, BFormInput, BButton } from "bootstrap-vue-next"
    import { Ref, ref } from 'vue';
    import api from '../api';
    import router from '../router';
    import { useModalStore } from "../stores";

    const modal = useModalStore()

    // reactive object linked to form above
    let registrationData:any = ref({
        email: '',
        password: '',
        password_confirmation: '',
        first_name: '',
        last_name: ''
    }).value

    let registerMode:Ref<Boolean> = ref(false) // reactive toggleable boolean whether or not to show registration fields

    async function login() {
        if (registrationData.email !== '' && registrationData.password !== '') {
            try {
                await api.auth.login(registrationData.email, registrationData.password) // only send email and password, object may contain registration data as well
                sessionStorage.setItem('userData', JSON.stringify(await api.user.get()))
                modal.hide()
                router.push('/')
            } catch (error:any) {
                modal.set(error.response.data.message)
            }
        }
        else {
            modal.set('Email and password are required.')
        }
    }

    async function register() {

        // check that all required fields are filled out
        let requiredFields:boolean = true;
        for (let [_key, value] of Object.entries(registrationData)) {
            if (value === '') {
                requiredFields = false
                modal.set('All fields are required.')
            }
        }

        // if they are then proceed sending request to api
        if (requiredFields) {
            try {
                await api.auth.register(registrationData)
                sessionStorage.setItem('userData', JSON.stringify(await api.user.get()))
                modal.hide()
                router.push('/')
            } catch (error:any) {
                modal.set(error.response.data.message)
            } 
        }
  
    }

</script>

<style>
    
</style>