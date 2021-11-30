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
      errorMsg: '',
      onlineAccountLogged:[],
      user: [],
      id: -1
    }
  },

  created: function () {
    AXIOS.get('/onlineAccountLoggedIn')
      .then(response => {
        this.onlineAccountLogged.push(response.data)
        this.id = response.data.accountId
      }) .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorMsg = errorMsg
        })
  },

  methods: {
    signOutUser: function(){
      AXIOS.post("/signOut").then(response =>
        {
          this.errorMsg= '';
          onlineAccountLogged = [];
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorMsg = errorMsg
        })
    },
    changeUsername: function(password, newUsername) {
      AXIOS.put('/changeUsername', {}, {params: {id: this.id, password: this.password, newUsername: this.newUsername}})
      .then(response => 
        {
          this.newUsername = this.password = this.errorOnlineAccount= ''
        })
        .catch(e => {
          this.usernameNew = this.password = ''
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorOnlineAccount = errorMsg
        })
    },
    changeEmail: function(password, newEmail) {
      AXIOS.put('/changeEmail', {}, {params: {id: this.id, password: this.password, newEmail: this.newEmail}})
      .then(response => 
        {
          this.newEmail = this.password = this.errorOnlineAccount= ''
        })
        .catch(e => {
          this.usernameNew = this.password = ''
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorOnlineAccount = errorMsg
        })
    },
    changePassword: function(password, newPassword) {
      AXIOS.put('/changePassword', {}, {params: {id: this.id, password: this.password, newPassword: this.newPassword}})
      .then(response => 
        {
          this.newPassword = this.password = this.errorOnlineAccount= ''
        })
        .catch(e => {
          this.usernameNew = this.password = ''
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorOnlineAccount = errorMsg
        })
    },
    deleteAccount: function(password) {
      AXIOS.delete('/deleteOnlineAccount', {}, {params: {id: this.id, password: this.password}})
      .then(response => 
        {
          this.password = this.errorOnlineAccount= ''
        })
        .catch(e => {
          this.usernameNew = this.password = ''
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorOnlineAccount = errorMsg
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

