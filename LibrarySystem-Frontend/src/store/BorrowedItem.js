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
function ItemDto (id, isArchived, isBorrowed, isDamaged){
  this.id = id;
  this.isArchived = isArchived;
  this.isBorrowed = isBorrowed;
  this.isDamaged = isDamaged;
}
function BookDto (id, title, author){
  this.id = id;
  this.title = title;
  this.author = author;
}
function AlbumDto (id, title, artist){
  this.id = id;
  this.title = title;
  this.artist = artist;
}
function MovieDto (id, title, director){
  this.id = id;
  this.title = title;
  this.director = director;
}


export default {
  name: 'borrowed-item',
    data () {
      return {
        items: [],
        books: [],
        albums: [],
        movies: [],
        response: [],
        onlineAccountLogged: [],
      }
  },
  created: function () {
    AXIOS.get('/onlineAccountLoggedIn')
      .then(response => {
        this.onlineAccountLogged.push(response.data)
      })
      my_id = onlineAccountLogged[onlineAccountLogged.length - 1].getUserId()
      AXIOS.get('/borrowedItems/books', {params: {id: my_id}})
      .then(response => {
        this.books.push(response.data)
      })
      AXIOS.get('/borrowedItems/albums', {params: {id: my_id}})
      .then(response => {
        this.albums.push(response.data)
      })
      AXIOS.get('/borrowedItems/movies', {params: {id: my_id}})
      .then(response => {
        this.movies.push(response.data)
      })
  },

  methods:{
  }
}