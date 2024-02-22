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
                        <em>{{ authStore.user.first_name }} {{ authStore.user.last_name }}</em>
                    </template>
                    <BDropdownItem><RouterLink to="/profile">Profile</RouterLink></BDropdownItem>
                    <BDropdownItem><RouterLink to="/login" @click="authStore.logout()">Logout</RouterLink></BDropdownItem>
                </BNavItemDropdown>
            </BNavbarNav>
        </BCollapse>
    </BNavbar>

    <slot />

</template>

<script setup lang="ts">
    import { BNavbar } from 'bootstrap-vue-next';
    import { useAuthStore } from '../stores';
    const authStore = useAuthStore()
</script>

<style>
</style>