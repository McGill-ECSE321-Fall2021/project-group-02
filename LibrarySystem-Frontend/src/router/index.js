import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import HomePage from '@/components/HomePage'
import SignUpPage from '@/components/SignUpPage'
import ManageItemsPage from '@/components/ManageItemsPage'
import UserProfilePage from '@/components/UserProfilePage'
import BorrowedItemsPage from '@/components/BorrowedItemsPage'

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
    {
      path: '/manageitems',
      name: 'ManageItemsPage',
      component: ManageItemsPage
    }
      path: '/userProfile',
      name: 'user-profile',
      component: UserProfilePage
    },
    {
      path: '/userProfile/borrowedItems',
      name: 'borrowed-items',
      component: BorrowedItemsPage
    },
  ]
})
