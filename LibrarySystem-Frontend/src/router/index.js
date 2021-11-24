import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import HomePage from '@/components/HomePage'
import SignUpPage from '@/components/SignUpPage'
import ViewItemsPage from '@/components/ViewItemsPage'
import UserProfilePage from '@/components/UserProfilePage'
import BorrowedItemsPage from '@/components/BorrowedItemsPage'
import ManageLibrariansPage from '@/components/ManageLibrariansPage'


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
      path: '/items',
      name: 'items',
      component: ViewItemsPage
    },
    {
      path: '/items/books',
      name: 'books',
      component: ViewItemsPage
    },
    {
      path: '/items/albums',
      name: 'albums',
      component: ViewItemsPage
    },
    {
      path: '/items/movies',
      name: 'movies',
      component: ViewItemsPage
    },
    {
      path: '/items/journals',
      name: 'journals',
      component: ViewItemsPage
    },
    {
      path: '/items/newspapers',
      name: 'newspapers',
      component: ViewItemsPage
    },
    {
      path: '/userProfile',
      name: 'user-profile',
      component: UserProfilePage
    },
    {
      path: '/librarians',
      name: 'ManageLibrariansPage',
      component: ManageLibrariansPage
    },
    {
      path: '/userProfile/borrowedItems',
      name: 'borrowed-items',
      component: BorrowedItemsPage
    }
  ]
})
