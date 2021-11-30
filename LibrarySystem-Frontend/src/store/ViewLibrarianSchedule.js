import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
function DailyScheduleDto (day, startTime, endTime) {
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
  
  }
  
  function WeeklyScheduleDto (startDate, endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  function LibrarianDto (firstname,lastname,address,city,id) {
	this.firstName = firstname
	this.lastName = lastname
	this.address = address
	this.city = city
	this.id = id
}

  export default {
    name: 'schedule',
    data () {
      return {
        weeklySchedules: [],
        dailySchedules: [],
        librarians: [],
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      dailyid: '',
      weeklyid: '',
      errorLibrarian: '',
      response: [],
      errorWeeklySchedules: '',
      errorDailySchedules: '',
      user: []
      }
    },
    created: function () {
      
      
      AXIOS.get('/librarians')
      .then(response => {
        // JSON responses are automatically parsed.
        this.librarians = response.data
        this.errorLibrarian = ''
      })
      .catch(e => {
        this.errorLibrarian = e
      })
      
    },

    
    methods: 
    {
        searchLibrarian: function (name) {
            AXIOS.get('/librarianSearch/'.concat('?librarianName=',name))
                  .then(response => {
                  // JSON responses are automatically parsed.
                    this.librarians = response.data
                    this.errorLibrarian = ''
                    this.name=''
                  })
                  .catch(e => {
                    var errorMsg = e
                    console.log(errorMsg)
                    this.errorLibrarian = errorMsg
                  })
              },

        getDailySchedules: function (id) {
            AXIOS.get('/schedulesdaily/'.concat('?LibID=', id))
                    .then(response => {
                      // JSON responses are automatically parsed.
                      this.dailySchedules = response.data
                      this.errorDailySchedules = ''
                      this.dailyid=''
                    })
                    .catch(e => {
                      var errorMsg = e
                      console.log(errorMsg)
                      this.errorDailySchedules = errorMsg
                    })
                },

          getWeeklySchedules: function (id) {
              AXIOS.get('/schedulesweekly/'.concat('?LibID=', id))
                    .then(response => {
                        this.weeklySchedules = response.data
                        this.errorWeeklySchedules = ''
                        this.weeklyid = ''
                    })
                    .catch(e => {
                        var errorMsg = e
                        console.log(errorMsg)
                        this.errorWeeklySchedules = errorMsg
                    })
                },
                getTypeOfUser(){
                  AXIOS.get('/onlineAccountLoggedInUser')
                  .then(response => {
                    this.user = response.data
                  }).catch(e => {
                    this.user = [];
                  })
                  return this.user;
                }

    }
}
