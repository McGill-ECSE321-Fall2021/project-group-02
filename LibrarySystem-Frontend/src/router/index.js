import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import HomePage from '@/components/HomePage'
// import SignUpPage from '@/components/SignUpPage'

import ManageLibrarySchedule from '@/components/ManageLibrarySchedule'
import CreatePatron from '@/components/CreatePatron'

import ManageItemsPage from '@/components/ManageItemsPage'

import ViewItemsPage from '@/components/ViewItemsPage'
//import UserProfilePage from '@/components/UserProfilePage'
import BorrowedItemsPage from '@/components/BorrowedItemsPage'
import Modal from '@/components/Modal'
import ManageLibrariansPage from '@/components/ManageLibrariansPage'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'WelcomePage',
      component: HomePage
    },
    // {
    //   path: '/signup',
    //   name: 'SignUpPage',
    //   component: SignUpPage
    // },
    {

      path: '/manageLibrarySchedule',
      name: 'ManageLibrarySchedule',
      component: ManageLibrarySchedule
    },
    {
      path: '/manageLibrarySchedule/librarians',
      name: 'librarians',
      component: ManageLibrarySchedule
    },
    {
      path: '/createPatron',
      name: 'CreatePatronPage',
      component: CreatePatron
    },
    {
       path: '/items',
      name: 'ViewItemsPage',

      path: '/manageitems',
      name: 'ManageItemsPage',
      component: ManageItemsPage,
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
    /*
    {
      path: '/userProfile',
      name: 'user-profile',
      component: UserProfilePage
    },
    */
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
