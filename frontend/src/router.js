import { createRouter, createWebHistory } from 'vue-router'
import Recipe from './components/Recipe.vue';

const routes = [
  {
    path: '/',
    name: 'recipe',
    component: Recipe
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title || 'FridgeTrack'
  next()
})

export default router
