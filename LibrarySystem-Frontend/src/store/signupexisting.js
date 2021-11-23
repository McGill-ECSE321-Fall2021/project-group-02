import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function OnlineAccountDto(username, password, email, user) {
  this.username = username;
  this.password = password;
  this.email = email;
  this.user = user;
}

export default {
  name: 'accountcreation',
  data () {
    return {
      onlineAccount: [],
      response: []
    }
  },
  created: function () {

  },
}