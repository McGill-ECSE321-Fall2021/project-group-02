import axios from 'axios'
var config = require('../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

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
function JournalDto (id, name, date){
  this.id = id;
  this.name = name;
  this.date = date;
}
function NewspaperDto (id, name, date){
  this.id = id;
  this.name = name;
  this.date = date;
}

export default {
    name: 'item',
    data () {
      return {
        items: [],
        books: [],
        albums: [],
        movies: [],
        journals: [],
        newspapers: [],
        response: []
      }
    },
    
    created: function () {
      //TEST ITEMS ADDED TO LISTS; DOES NOT TEST BACKEND INTEGRATION
      const b1 = new BookDto(1, "TEST BOOK", "TEST_AUTHOR")
      this.books = [b1]
      const a1 = new AlbumDto(2, "TEST ALBUM", "TEST_ARTIST")
      this.albums = [a1]
      const m1 = new MovieDto(3, "TEST MOVIE", "TEST_DIRECTOR")
      this.movies = [m1]
      const n1 = new NewspaperDto(4, "TEST NEWSPAPER", new Date)
      this.newspapers = [n1]
      const j1 = new JournalDto(5, "TEST JOURNAL", new Date)
      this.journals = [j1]
      
      AXIOS.get('/items')
      .then(response => {
        this.items = response.data
      })
      AXIOS.get('/items/books')
      .then(response => {
        this.books = response.data
      })
      AXIOS.get('/items/albums')
      .then(response => {
        this.albums = response.data
      })
      AXIOS.get('/items/movies')
      .then(response => {
        this.movies = response.data
      })
      AXIOS.get('/items/newspapers')
      .then(response => {
        this.newspapers = response.data
      })
      AXIOS.get('/items/journals')
      .then(response => {
        this.journals = response.data
      })
    }
}
