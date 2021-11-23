import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import HomePage from '@/components/HomePage'
import SignUpPage from '@/components/SignUpPage'
import ManageLibrarySchedule from '@/components/ManageLibrarySchedule'
import CreatePatron from '@/components/CreatePatron'
import ViewItemsPage from '@/components/ViewItemsPage'
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
      component: ViewItemsPage
    },
    {
      path: '/userProfile',
      name: 'user-profile',
      component: UserProfilePage
    },
    {
      path: '/userProfile/borrowedItems',
      name: 'borrowed-items',
      component: BorrowedItemsPage
    }
  ]
})
