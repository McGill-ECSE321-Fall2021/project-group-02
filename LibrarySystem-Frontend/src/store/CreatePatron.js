import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function PatronDto(id, address, city, firstName, lastName, balance) {
  this.id = id;
  this.address = address;
  this.city = city;
  this.firstName = firstName;
  this.lastName = lastName;
  this.balance = balance;
}

export default {
    name: 'createPatron',
    data () {
      return {
        onlineAccounts: [],
        response: [],
        success: '',
        errorMsg: '',
        user: []
      }
    },
    created: function () {
    },
    methods: {
      createPatron: function(firstName, lastName, city, address) {
        var patron = AXIOS.post('/createPatron/'.concat(address, '/?city=', city, '&balance=', 0, '&firstName=', firstName, '&lastName=', lastName))
        .then(response => {
          this.firstName = ''
          this.lastName = ''
          this.city = ''
          this.address = ''
          this.errorMsg = ''

          var success = response.data.id
          this.success = success
        })
        .catch(e => {
          var errorMsg = e
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
