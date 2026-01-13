import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import OrderPage from './views/OrderPage.vue'
import AdminPage from './views/AdminPage.vue'
import PickupPage from './views/PickupPage.vue'

const routes = [
  { path: '/', component: OrderPage },
  { path: '/admin', component: AdminPage },
  { path: '/pickup', component: PickupPage }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

createApp(App).use(router).mount('#app')
