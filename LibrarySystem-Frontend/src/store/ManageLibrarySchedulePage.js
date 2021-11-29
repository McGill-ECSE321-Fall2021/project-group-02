import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function LibrarianDto (firstname,lastname,address,city,id) {
	this.firstName = firstname
	this.lastName = lastname
	this.address = address
	this.city = city
	this.id = id
}

function DailyScheduleDto (day, startTime, endTime, id) {
  this.day = day;
  this.startTime = startTime;
  this.endTime = endTime;
  this.id = id;

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
      errorDailySchedule: ''
      
    }
  },
  created: function () {
    // Test data
    const p1 = new LibrarianDto('John', 'Park', 'TestAddress', 'TestCity', 5)
    const l1 = new LibrarianDto('YounessBellali', 'Doe', 'TestAddress1', 'TestCity1', 1)
    const l3 = new LibrarianDto('Jane', 'Doe', 'TestAddress1', 'TestCity1', 2)
    const l5 = new LibrarianDto('Jane', 'Doe', 'TestAddress1', 'TestCity1', 6)
    const l6 = new LibrarianDto('Jane', 'Doe', 'TestAddress1', 'TestCity1', 6)
    this.librarians = [p1, l1, l3, l5, l6]


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
    createDailySchedule: function (day, startTime, endTime, id) {
      AXIOS.post('/createDailySchedule/'.concat(id,'/',day,'/',startTime,'/',endTime))
      .then(response => {
        this.dailySchedules.push(response.data)
        this.errorDailySchedule = ''
          this.day = ''
          this.startTime = ''
          this.endTime= ''
          this.id = ''
      })
      .catch(e => {
        var errorMsg = e.response.data.message
        console.log(errorMsg)
        this.errorDailySchedule = errorMsg
      })
    }
  }
}