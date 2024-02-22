<template>

    <div class="centerContainer">
        <h2>Welcome to TipTracker</h2>

        <BForm @submit="(e) => {e.preventDefault()}">
            
            <!-- always shows -->
            <BFormInput
                id="email-input"
                v-model="loginRegData.email"
                type="email"
                placeholder="Email"
                required
            />
            <BFormInput
                id="password-input"
                v-model="loginRegData.password"
                type="password"
                placeholder="Password"
                required
            />

            <!-- shows only if in register mode -->
            <BFormInput
                id="password-confirmation-input"
                v-if="registerMode"
                v-model="loginRegData.password_confirmation"
                type="password"
                placeholder="Confirm Password"
            />
            <BFormInput
                id="first-name-input"
                v-if="registerMode"
                v-model="loginRegData.first_name"
                placeholder="First Name"
            />
            <BFormInput
                id="last-name-input"
                v-if="registerMode"
                v-model="loginRegData.last_name"
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
    import { BForm, BFormInput, BButton } from "bootstrap-vue-next"
    import { Ref, ref } from 'vue';
    import router from '../router';
    import { useAuthStore, useModalStore } from "../stores";
    import { LoginOrRegistrationData } from "../types";

    const authStore = useAuthStore()
    const modalStore = useModalStore()

    // if user is already logged in, redirect to dashboard
    if (authStore.isLoggedIn) {
        router.push('/')
    }

    const loginRegData:Ref<LoginOrRegistrationData> = ref({} as LoginOrRegistrationData)
    const registerMode:Ref<Boolean> = ref(false) // reactive toggleable boolean whether or not to show registration fields

    async function login() {
        const [success, reason] = await authStore.login(loginRegData.value)
        if (success) {
            router.push('/')
        }
        else {
            modalStore.set(reason)
        }
    }

    async function register() {
        const [success, reason] = await authStore.register(loginRegData.value)
        if (success) {
            router.push('/')
        }
        else {
            modalStore.set(reason)
        }
    }

</script>

<style>
    
</style>