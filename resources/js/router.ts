import { Router, createRouter, createWebHashHistory } from 'vue-router'
import LoginRegister from './pages/LoginRegister.vue'
import AddShift from './pages/AddShift.vue'
import Home from './pages/Home.vue'
import Profile from './pages/Profile.vue'
import { useModalStore } from './stores'

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
        },
        {
            path: '/profile',
            component: Profile,
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
    useModalStore().hide() // reset modal on each page navigation
});

export default router;