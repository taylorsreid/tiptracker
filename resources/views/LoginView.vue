<template>

    <h1>Tip Tracker</h1>

    <!-- <label for="email">email</label> -->
    <input type="email" v-model="email" placeholder="Email" required>

    <br>

    <!-- <label for="password">password</label> -->
    <input type="password" v-model="password" placeholder="Password" required>

    <br>

    <label for="remember">Remember</label>
    <input type="checkbox" id="remember" v-model="remember" />

    <br>

    <button type="submit" @click="login">Login</button>
    <br>
    <button @click="logout">Logout</button>

    
</template>
<script setup>

    import axios from 'axios';
    import Cookies from "js-cookie";
    import { ref } from 'vue'   
    import { useRouter } from 'vue-router' 

    const router = useRouter();
    const email = ref('');
    const password = ref('');
    const remember = ref(false);

    //login
    async function login() {
        axios.get('/sanctum/csrf-cookie').then(() => { // get csrf cookie
            axios.post('/auth/login', { // call login endpoint
                email : email.value,
                password : password.value,
                remember : remember.value
            })
            .then(() => {
                axios.get('/user').then((response) => { // call user endpoint
                    sessionStorage.setItem('user', JSON.stringify(response.data));
                    // router.push('/home');
                })
                .catch((error) => {

                })
            })
            .catch((error) => {
                console.log(error);
            })
        });
    }

    function logout() {
        axios.post('/auth/logout').then(() => {
            Cookies.remove('XSRF-TOKEN');
            sessionStorage.clear();
        })
    }

</script>