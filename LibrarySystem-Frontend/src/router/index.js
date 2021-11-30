import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import HomePageLogin from '@/components/HomePageUser'
import HomePageLibrarian from '@/components/HomePageLibrarian'
import SignUpPage from '@/components/SignUpPage'

import ManageLibrarySchedule from '@/components/ManageLibrarySchedule'
import CreatePatron from '@/components/CreatePatron'

import ManageItemsPage from '@/components/ManageItemsPage'

import ViewItemsPage from '@/components/ViewItemsPage'
import UserProfilePage from '@/components/UserProfilePage'
import UserProfilePageLibrarian from '@/components/UserProfilePageLibrarian'
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
      path: '/homeAfterLogin',
      name: 'HomeAfterLogin',
      component: HomePageLogin
    },
    {
      path: '/signup',
      name: 'SignUpPage',
      component: SignUpPage
    },
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
    
    {
      path: '/userProfile',
      name: 'user-profile',
      component: UserProfilePage
    },
    
    {
      path: '/userProfileLibrarian',
      name: 'user-profile-librarian',
      component: UserProfilePageLibrarian
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
    },
    {
      path: '/borrowedItems/books',
      name: 'borrowed-books',
      component: BorrowedItemsPage
    },
    {
      path: '/borrowedItems/albums',
      name: 'borrowed-albums',
      component: BorrowedItemsPage
    },
    {
      path: '/borrowedItems/movies',
      name: 'borrowed-movies',
      component: BorrowedItemsPage
    },
    {
      path: '/homePageLibrarian',
      name: 'home-page-librarian',
      component: HomePageLibrarian
    }
  ]
})
