<template>
    <MDBNavbar expand="lg" light bg="light" position="top" container>

      <MDBNavbarNav right>
        <!-- Avatar -->
        <MDBDropdown class="nav-item" v-model="dropdown6">
          <MDBDropdownToggle tag="a" class="nav-link" @click="dropdown6 = !dropdown6" right>
            <img
              src="https://mdbootstrap.com/img/Photos/Avatars/img (31).webp"
              class="rounded-circle"
              height="22"
              alt=""
              loading="lazy"
            />
          </MDBDropdownToggle>
          <MDBDropdownMenu right>
            <MDBDropdownItem href="#">My profile</MDBDropdownItem>
            <MDBDropdownItem href="#">Settings</MDBDropdownItem>
            <MDBDropdownItem href="#" @click="$emit('logoutClick')">Logout</MDBDropdownItem>
          </MDBDropdownMenu>
        </MDBDropdown>
      </MDBNavbarNav>

      

    </MDBNavbar>
  </template>

<script setup lang="ts">
  import {
    MDBNavbar,
    MDBNavbarNav,
    MDBNavbarItem,
    MDBDropdown,
    MDBDropdownToggle,
    MDBDropdownMenu,
    MDBDropdownItem,
  } from 'mdb-vue-ui-kit';
  import { ref } from 'vue';

  const dropdown6 = ref(false);
</script>

<style>
</style>