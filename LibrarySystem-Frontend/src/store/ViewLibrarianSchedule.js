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
      id: '',
      errorLibrarian: '',
      response: []
      }
    },
    created: function () {
      AXIOS.get('/schedules')
      .then(response => {
        this.weeklySchedules = response.data
      })
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
    }
}
