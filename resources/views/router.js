import { createRouter, createWebHistory } from 'vue-router'

export default createRouter({
  history: createWebHistory(),
  routes: [

    {
      path: '/login',
      component: () => import('./Login.vue'),
    },
    {
      path: '/home',
      component: () => import('./Home.vue'),
    }
  ],
})
