import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function OnlineAccountDto(username, password, email, userId, address, firstName, lastName, balance, city) {
  this.username = username;
  this.password = password;
  this.email = email;
  this.userId = userId;
  this.address=address;
	this.firstName=firstName;
	this.lastName=lastName;
	this.balance=balance;
  this.city=city;
}



export default {
  name: 'accountcreation',
  data () {
    return {
      onlineAccounts: [],
      username: '',
      password: '',
      usernameLogin: '',
      passwordLogin: '',
      usernameExisting: '',
      passwordExisting: '',
      emailExisting: '',
      email: '',
      userId: '',
      address: '',
	    firstName: '',
	    lastName: '',
	    balance: '',
      city: '',
      errorOnlineAccount: '',
      response: []
    }
  },

  methods: {
    createAccountNewUser: function(username, password, email, address, city, firstName, lastName) {
      AXIOS.post('/onlineAccountNew/'.concat(firstName, '/', lastName, '/', address, '/', city, '/', username, '/', password, '/', email))
      .then(response => 
        {
          this.onlineAccounts.push(response.data)
          this.errorOnlineAccount= ''
          this.username = ''
          this.password = ''
          this.email = ''
          this.address= ''
          this.firstName= ''
          this.lastName= ''
          this.city= ''
        })
        .catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorOnlineAccount = errorMsg
        })
      },
      
      logInUser: function(username, password) {
        AXIOS.post('/logIn/'.concat(username, '/', password)).then(response => 
          {
            this.onlineAccounts.push(response.data);
            this.errorOnlineAccount= '';
            this.usernameLogin = '';
            this.passwordLogin = '';
            this.email = '';
            this.userId = '';
            this.address= '';
            this.firstName= '';
            this.lastName= '';
            this.balance= '';
            this.city= '';
            //Needs to be linked to the user profile page
            this.$router.push("/");
          })
          .catch(e => {
            var errorMsg = e.response.data.message;
            console.log(errorMsg);
            this.errorOnlineAccount = errorMsg;
          })
        },
      

      createAccountExistingUser: function(username, password, email, userId) {
        AXIOS.post('/onlineAccountExisting/'.concat(userId, '/', username, '/', password, '/', email)).then(response => 
          {
            this.onlineAccounts.push(response.data)
            this.errorOnlineAccount= ''
            this.usernameExisting = ''
            this.passwordExisting = ''
            this.emailExisting = ''
            this.userId = ''
            this.address= ''
            this.firstName= ''
            this.lastName= ''
            this.balance= ''
            this.city= ''
          })
          .catch(e => {
            var errorMsg = e.response.data.message
            console.log(errorMsg)
            this.errorOnlineAccount = errorMsg
          })
        },

  }
}