import { Router, createRouter, createWebHashHistory } from 'vue-router'
import LoginRegister from './pages/LoginRegister.vue'
import AddShift from './pages/AddShift.vue'
import Home from './pages/Home.vue'

let router:Router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            component: Home,
            meta : {
                isPublic: false
            }
        },
        {
            path: '/login',
            component: LoginRegister,
            meta : {
                isPublic: true
            }
        },
        {
            path: '/addshift',
            component: AddShift,
            meta : {
                isPublic: false
            }
        }
    ]
})

router.beforeEach((to) => {
    if (!to.meta.isPublic && sessionStorage.getItem('userData') === null) {
        router.push('/login')
    }
});

export default router;