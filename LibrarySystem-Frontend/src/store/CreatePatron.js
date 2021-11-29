import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function OnlineAccountDto(username, password, email, userId, address, firstName, lastName, balance) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.userId = userId;
    this.address=address;
    this.firstName=firstName;
    this.lastName=lastName;
    this.balance=balance;
  }

  function PatronDto(id, address, city, firstName, lastName, balance) {
      this.id = id;
      this.address = address;
      this.city = city;
      this.firstName = firstName;
      this.lastName = lastName;
      this.balance = balance;
  }

export default {
    name: 'accountcreation',
    data () {
      return {
        onlineAccounts: [],
        response: []
      }
    },

    createAccountNewUser: function(username, password, email, userId, address, firstName, lastName, balance) {
        AXIOS.post('/onlineAccountNew/',null, {params: {username, password, email, userId, address, firstName, lastName, balance}}).then(response => 
          {
            this.onlineAccounts.push(response.data)
            
          }
          ).catch(e => {
          var errorMsg = e.response.data.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        });
      }
}