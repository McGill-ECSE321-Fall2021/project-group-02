import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import HomePage from '@/components/HomePage'
import SignUpPage from '@/components/SignUpPage'

Vue.use(Router)



export default new Router({
  routes: [
    {
      path: '/',
      name: 'WelcomePage',
      component: HomePage
    },
    {
      path: '/signup',
      name: 'SignUpPage',
      component: SignUpPage
    },
  ]
})
