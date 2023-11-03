import { createRouter, createWebHashHistory } from 'vue-router'
import Dashboard from './components/Dashboard.vue'
import LoginRegister from './components/LoginRegister.vue'

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            component: LoginRegister
        },
        {
            path: '/dashboard',
            component: Dashboard
        }
    ]
})