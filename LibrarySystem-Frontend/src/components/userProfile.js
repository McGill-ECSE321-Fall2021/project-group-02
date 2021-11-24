/*
import axios from 'axois'

var config = require(../../config)
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

created: function() {
    // Initializing persons from backend
    AXIOS.delete('/deleteOnlineAccountUsername')
    .then(response => {
      
    })
    .catch(e => {
      
    })
  }


function OnlineAccountDto(username, password, email, user){
  this.username = username;
  this.password = password;
  this.email = email;
  this.user = user;
}

function PatronDto(id, address, city, firstName, lastName, balance){
  this.id = id;
  this.address = address;
  this.city = city;
  this.firstName = firstName;
  this.lastName = lastName;
  this.balance = balance;
}

function HeadLibrarianDto(onlineAccount,firstName, lastName, address, city, balance, weeklySchedule, id){
  this.onlineAccount = onlineAccount;
  this.firstName = firstName;
  this.lastName = lastName;
  this.address = address;
  this.city = city; 
  this.balance = balance;
  this.weeklySchedule = weeklySchedule;
  this.id = id;
}

function LibrarianDto(onlineAccount,firstName, lastName, address, city, balance, weeklySchedule, id){
  this.onlineAccount = onlineAccount;
  this.firstName = firstName;
  this.lastName = lastName;
  this.address = address;
  this.city = city; 
  this.balance = balance;
  this.weeklySchedule = weeklySchedule;
  this.id = id;
}

export default {
  name: 'userprofile',
  data () {
    return {
      newUsername:'',
      newPassword:'',
      newEmail:'',
      response: [],
      email: '',
      password: '',
      username: '',
      emailUsername: ''
    }
  },
  //...
}

methods: {
    createPerson: function (personName) {
      // Create a new person and add it to the list of people
      var p = new PersonDto(personName)
      this.persons.push(p)
      // Reset the name field for new people
      this.newPerson = ''
    }
  }
  */