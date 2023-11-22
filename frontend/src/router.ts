import { createRouter, createWebHashHistory } from 'vue-router'
import Dashboard from './components/Dashboard.vue'
import LoginRegister from './components/LoginRegister.vue'
import AddShift from './components/AddShift.vue'
import Home from './components/Home.vue'

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/login',
            component: LoginRegister
        },
        {
            path: '/',
            component: Dashboard,
            children: [
                {
                    path: 'home',
                    component: Home
                },
                {
                    path: 'addshift',
                    component: AddShift
                }
            ]
        }
    ]
})