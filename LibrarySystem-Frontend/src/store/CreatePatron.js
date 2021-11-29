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

        errorMsg: '',
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
          this.success = 'Success! Patron has been created. Patron ID: '.concat(patron.id)
        })
        .catch(e => {
          var errorMsg = e
          console.log(errorMsg)
          this.errorMsg = errorMsg
        })
      }
    }

}
