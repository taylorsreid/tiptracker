import { createRouter, createWebHistory } from 'vue-router'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/web',
      name: 'home',
      component: () => import('./HomeView.vue')
    },
    {
      path: '/web/login',
      name: 'login',
      component: () => import('./LoginView.vue')
    }
  ],
})
