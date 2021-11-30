import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function HeadLibrarianDto(onlineAccount, firstName, lastName, address, city, balance, weeklySchedule, id){
    this.onlineAccount=onlineAccount;
	this.firstName=firstName;
	this.lastName=lastName;
	this.address=address;
	this.city=city;
	this.balance=balance;
	this.weeklySchedule=weeklySchedule;
	this.id=id;
}

export default {
    name: 'homepage',
    data () {
      return {
        errorMsg: '',
        response: []
      }
    },

    created: function () {
        AXIOS.post('/createHeadLibrarian')
          .then(response => {
          })
      },
  
  }