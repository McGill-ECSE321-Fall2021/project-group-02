import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function OnlineAccountDto(username, password, email, userId, address, firstName, lastName, balance, city, loggedIn, accountId) {
  this.username = username;
  this.password = password;
  this.email = email;
  this.userId = userId;
  this.address=address;
	this.firstName=firstName;
	this.lastName=lastName;
	this.balance=balance;
  this.city=city;
  this.loggedIn=loggedIn;
  this.accountId=accountId;
}

function PatronDto(id, address, city, firstName, lastName, balance) {
  this.id = id;
  this.address = address;
  this.city = city;
  this.firstName = firstName;
  this.lastName = lastName;
  this.balance = balance;
}

function HeadLibrarianDto(onlineAccount, firstName, lastName, address, city, balance, weeklySchedule, id) {
  this.onlineAccount = onlineAccount;
  this.firstName = firstName;
  this.lastName = lastName;
  this.address = address;
  this.city = city;
  this.balance = balance;
  this.weeklySchedule = weeklySchedule;
  this.id = id;
}

function LibrarianDto(onlineAccount, firstName, lastName, address, city, balance, weeklySchedule, id) {
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
  data() {
    return {
      newUsername: '',
      newPassword: '',
      newEmail: '',
      response: [],
      email: '',
      password: '',
      username: '',
      emailUsername: '',
      onlineAccount:[],
    }
  },

  created: function () {
    AXIOS.get('/onlineAccountLoggedIn')
      .then(response => {
        this.onlineAccount = response.data
      })
  }
  

}

