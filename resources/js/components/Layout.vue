<template>

    <BNavbar toggleable="lg" variant="success" v-b-color-mode="'light'">
        <BNavbarBrand to="/">TipTracker</BNavbarBrand>
        <BNavbarToggle target="nav-collapse" />
        <BCollapse id="nav-collapse" is-nav>
            <!-- Right aligned nav items -->
            <BNavbarNav class="ms-auto mb-2 mb-lg-0">
                <BNavItemDropdown right>
                    <!-- Using 'button-content' slot -->
                    <template #button-content>
                        <em>{{ userData?.first_name }} {{ userData?.last_name }}</em>
                    </template>
                    <BDropdownItem href="#">Profile</BDropdownItem>
                    <BDropdownItem @click="logout">Logout</BDropdownItem>
                </BNavItemDropdown>
            </BNavbarNav>
        </BCollapse>
    </BNavbar>

    <slot></slot>

    <Error :error-message="errorMessage" v-show="errorMessage !== ''" @hideError="errorMessage = ''" />

</template>

<script setup lang="ts">
    import { Ref, ref } from 'vue';
    import Error from './Error.vue';
    import { BNavbar } from 'bootstrap-vue-next';
    import api from '../api';
    import router from '../router';
    import { User } from '../types';

    let errorMessage:Ref<string> = ref('')
    let stringData:string | null = sessionStorage.getItem('userData')
    let userData:User;
    if (stringData !== null) {
        userData = JSON.parse(stringData)
    }

    async function logout() {
        await api.logout()
        sessionStorage.clear()
        router.push('/login')
    }
</script>

<style>
</style>