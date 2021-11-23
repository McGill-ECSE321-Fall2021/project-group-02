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
    const l1 = new LibrarianDto('John','Doe','123 Bro Street','Montreal',2)
    const l2 = new LibrarianDto('Jill','Valentine','345 Bruh Street','Montreal',3)
    // Sample initial content
    this.librarians = [l1, l2]
  },

  methods: {
    createLibrarian: function (fn,ln,ad,city,id) {
      // Create a new person and add it to the list of people
      var l = new LibrarianDto(fn,ln,ad,city,id)
      this.librarians.push(l)
      // Reset the name field for new people
      this.firstName = ''
      this.lastName = ''
      this.address = ''
      this.city = ''
      this.id = ''
    }
  }
}
