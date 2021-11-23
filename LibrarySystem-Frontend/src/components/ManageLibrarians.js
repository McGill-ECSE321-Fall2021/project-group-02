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

function HeadLibrarianDto (firstname,lastname,address,city,id) {
	this.firstName = firstname
	this.lastName = lastname
	this.address = address
	this.city = city
	this.id = id
}

export default {
  name: 'librarysystem',
  data () {
    return {
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
    createLibrarian: function (fn,ln,ad,city,id) {
	AXIOS.post('/createLibrarian/'.concat(id,'/',fn,'/',ln,'/',ad,'/',city))
        .then(response => {
        // JSON responses are automatically parsed.
          this.librarians.push(response.data)
          this.errorLibrarian = ''
          this.firstName = ''
          this.lastName = ''
          this.address= ''
          this.city = ''
          this.id = ''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorLibrarian = errorMsg
        })
    },
    deleteLibrarian: function(userid,validationid){
     AXIOS.delete('/deleteLibrarian'.concat(validationid),{userid}).then(response => {
       this.librarians.push(response.data)
       this.errorLibrarian = ''
       this.userid = ''
       this.validationid = ''
     })
     .catch(e => {
       var errorMsg = e.response.data.message
       console.log(errorMsg)
       this.errorLibrarian = errorMsg
     })   
    }
  }
}
