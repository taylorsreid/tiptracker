<template>
    <MDBNavbar expand="lg" light bg="light" position="top" container>
        <MDBNavbarNav right>
            <MDBDropdown class="nav-item" v-model="dropdown6">
                <MDBDropdownToggle tag="a" class="nav-link" @click="dropdown6 = !dropdown6" right>
                    {{ userData?.first_name }} {{ userData?.last_name }}
                </MDBDropdownToggle>
                <MDBDropdownMenu right>
                    <MDBDropdownItem href="#" @click="">My Profile</MDBDropdownItem>
                    <MDBDropdownItem href="#" @click="logout">Logout</MDBDropdownItem>
                </MDBDropdownMenu>
            </MDBDropdown>
        </MDBNavbarNav>
    </MDBNavbar>
</template>

<script setup lang="ts">
    import {
        MDBNavbar,
        MDBNavbarNav,
        MDBDropdown,
        MDBDropdownToggle,
        MDBDropdownMenu,
        MDBDropdownItem,
    } from 'mdb-vue-ui-kit';
    import { ref } from 'vue';
    import api from '../api';
    import router from '../router';
    const dropdown6 = ref(false);

    defineProps({
        userData:Object
    })

    async function logout() {
        await api.logout()
        sessionStorage.clear()
        router.push('/')
    }
</script>

<style></style>