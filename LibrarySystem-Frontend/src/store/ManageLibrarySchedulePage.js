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

function DailyScheduleDto (day, startTime, endTime) {
  this.day = day;
  this.startTime = startTime;
  this.endTime = endTime;

}

function WeeklyScheduleDto (startDate, endDate) {
  this.startDate = startDate;
  this.endDate = endDate;
}

export default {
  name: 'librarysystem',
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
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      id: '',
      errorLibrarian: '',
      response: [],
      dailySchedules: [],
      errorDailySchedule: '',

      startDate:'',
      endDate: '',

      startTime:'',
      endTime: '',

      day: '',
      errorMsg: '',

      librarianID: '',

      weeklySchedules: [],
      success: '',

      user: []
      
    }
  },
  created: function () {
    // Test data
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

  methods: {
    createDailySchedule: function (day, startTime, endTime, id, headLibrarianID) {
      AXIOS.post('/createDailySchedule/'.concat(headLibrarianID, '/', id,'/',day,'/',startTime,'/',endTime))
      .then(response => {
        this.dailySchedules.push(response.data)
        this.errorDailySchedule = ''
          this.day = ''
          this.startTime = ''
          this.endTime= ''
          this.id = ''
          this.headLibrarianID = ''
          var success = response.data
          this.sucess = success

      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorDailySchedule = errorMsg
      })
    },

    createWeeklySchedule: function (headLibrarianID, startDate, endDate, librarianID) {
      AXIOS.post('/createWeeklySchedule/'.concat(headLibrarianID, '/', startDate, '/', endDate, '/', librarianID))
      .then(response => {
        this.weeklySchedules.push(response.data)
        this.startDate = ''
        this.endDate = ''
        this.librarianID = ''
        this.headLibrarianID = ''

        var success = response.data
        this.sucess = success
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorMsg = errorMsg
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