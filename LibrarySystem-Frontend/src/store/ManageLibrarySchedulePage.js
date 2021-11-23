import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function LibrarianDto (onlineAccount, firstName, lastName, address, city, weeklySchedule, balance, id){
    this.onlineAccount = onlineAccount;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.weeklySchedule = weeklySchedule;
    this.balance = balance;
    this.id = id;
  
}

export default {
    name: 'librarian',
    data () {
        const now = new Date()
        const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
        const minDate = new Date(today)
        const maxDate = new Date(today)
        maxDate.setMonth(maxDate.getMonth() + 1)


        return {
        value: '',
        min: minDate,
        max: maxDate,
        librarians: [],
        response: []
      }
    },
    
    created: function () {
      //TEST ITEMS ADDED TO LISTS; DOES NOT TEST BACKEND INTEGRATION
      const l1 = new LibrarianDto(new OnlineAccount, "TEST_FIRSTNAME", "TEST_LASTNAME", "TEST_ADDRESS", "TEST_CITY", new WeeklySchedule, 0, 1)
      this.librarians = [l1]
      
      
      AXIOS.get('/manageLibrarySchedule')
      .then(response => {
        this.librarians = response.data
      })
    }
}