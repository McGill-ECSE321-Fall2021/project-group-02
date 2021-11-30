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
function BookDto (title, author, isAvailable, id){
  this.id = id;
  this.title = title;
  this.author = author;
  this.isAvailable = isAvailable;
}
function AlbumDto (id, title, artist, isAvailable){
  this.id = id;
  this.title = title;
  this.artist = artist;
  this.isAvailable = isAvailable;
}
function MovieDto (id, title, director, isAvailable){
  this.id = id;
  this.title = title;
  this.director = director;
  this.isAvailable = isAvailable;
}


export default {
  name: 'borrowed-item',
    data () {
      return {
        books: [],
        albums: [],
        movies: [],
        response: [],
        onlineAccountLogged: [],
        userId: -1,
      }
  },
  created: function () {
    AXIOS.get('/onlineAccountLoggedIn')
      .then(response => {
        this.onlineAccountLogged.push(response.data)
        this.userId = response.data.userId
      })
      AXIOS.get('/borrowedItems/books/'.concat('?id=', this.userId))
      .then(response => {
        this.books = response.data
      })
      AXIOS.get('/borrowedItems/albums', {}, {params: {id: this.userId}})
      .then(response => {
        this.albums = response.data
      })
      AXIOS.get('/borrowedItems/movies', {}, {params: {id: this.userId}})
      .then(response => {
        this.movies = response.data
      })
  },

  methods:{
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
  }
}