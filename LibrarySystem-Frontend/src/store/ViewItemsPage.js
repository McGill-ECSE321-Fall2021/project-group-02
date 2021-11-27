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
    },
    methods: 
    {
      searchItems(searchTerm, searchType){
        if (searchType.localeCompare("Name") == 0){
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
        } else if(searchType.localeCompare("None") == 0){
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
        /*
        if (searchType.localeCompare("ID") == 0) {
          for(i = 0; i < books.length; i++){
            if(books[i].id == searchTerm){ 
              this.books= books[i];
              albums = [];
              movies = [];
              journals = [];
              newspapers = [];
            }
          }
          for(i = 0; i < albums.length; i++){
            if(albums[i].id == searchTerm){ 
              this.albums= albums[i];
              books = [];
              movies = [];
              journals = [];
              newspapers = [];
            }
          }
          for(i = 0; i < movies.length; i++){
            if(movies[i].id == searchTerm){ 
              this.movies= movies[i];
              albums = [];
              books = [];
              journals = [];
              newspapers = [];
            }
          }
          for(i = 0; i < journals.length; i++){
            if(journals[i].id == searchTerm){ 
              this.journals= journals[i];
              albums = [];
              movies = [];
              books = [];
              newspapers = [];
            }
          }
          for(i = 0; i < newspapers.length; i++){
            if(newspapers[i].id == searchTerm){ 
              this.newspapers = newspapers[i];
              albums = [];
              movies = [];
              journals = [];
              books = [];
            }
          }
          
        }
        */
       
      }
    }
}
