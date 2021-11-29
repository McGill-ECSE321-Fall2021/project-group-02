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
      AXIOS.get('/items')
      .then(response => {
        this.items = response.data
      })
      // Gets all the items from the database
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
    },
    methods: 
    {
      searchItems(searchTerm, searchType){// Search items by type and term
        if (searchType.localeCompare("Name") == 0){// Only Include items by the specified name
          AXIOS.get('/items/books/'.concat('?title=', searchTerm)).then(response =>{
            this.books = response.data
          })
          AXIOS.get('/items/albums/'.concat('?title=', searchTerm)).then(response =>{
            this.albums = response.data
          })
          AXIOS.get('/items/movies/'.concat('?title=', searchTerm)).then(response =>{
            this.movies = response.data
          })
          AXIOS.get('/items/newspapers/'.concat('?name=', searchTerm)).then(response =>{
            this.newspapers = response.data
          })
          AXIOS.get('/items/journals/'.concat('?name=', searchTerm)).then(response =>{
            this.journals = response.data
          })
        } else if (searchType.localeCompare("Creator") == 0){
          AXIOS.get('/items/books/'.concat('?author=', searchTerm)).then(response =>{
            this.books = response.data
          })
          AXIOS.get('/items/albums/'.concat('?artist=', searchTerm)).then(response =>{
            this.albums = response.data
          })
          AXIOS.get('/items/movies/'.concat('?director=', searchTerm)).then(response =>{
            this.movies = response.data
          })
        } else if(searchType.localeCompare("None") == 0){// Reset displayed items to all the ones in the database
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
        searchTerm = '';
      }
    }
}