import axios from 'axios'
import e from 'express'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function ItemDto (id, isArchived, isBorrowed, isDamaged){
    this.id = id
    this.isArchived = isArchived
    this.isBorrowed = isBorrowed
    this.isDamaged = isDamaged
  }

function BookDto (title, author){
    this.title = title
    this.atuhor = author
  }

function MovieDto (title, director) {
    this.title = title
    this.director = director
}

function AlbumDto (title, artist) {
    this.title = title
    this.artist = artist
}

function NewspaperDto (name, date) {
    this.name = name
    this.date = date
}

function JournalDto (name, date) {
    this.name = name
    this.date = date
}

function PatronDto (id){
    this.id = id
    this.borrowedItems = []
}

  export default {
    name: 'mamageItem',
    data () {
      return {
        items: [],
        itemID = '',
        itemName = '',
        itemAuthor = '',
        itemType = '',
        isArchived = '',
        isBorrowed = '',
        isDamaged = '',

        patrons: [],
        patronID = '',
        librarians: [],
        librarianID = '',

        response= [],
        errorMsg = '',
      }
    },

    created: function () {
        AXIOS.get('/items')
        .then(response => {
          // JSON responses are automatically parsed.
          this.items = response.data
          this.errorMsg = ''
        })
        .catch(e => {
          this.errorMsg = e
        })

        AXIOS.get('/patrons')
        .then(response => {
          // JSON responses are automatically parsed.
          this.patrons = response.data
          this.errorMsg = ''
        })
        .catch(e => {
          this.errorMsg = e
        })

        AXIOS.get('/librarians')
        .then(response => {
          // JSON responses are automatically parsed.
          this.librarians = response.data
          this.errorMsg = ''
        })
        .catch(e => {
          this.errorMsg = e
        })
    },

    methods: {
        borrowItem: function(itemID, patronID) {

        },

        returnItem: function(itemID, patronID) {
            var indexItem = this.items.map(x => x.id).indexOf(itemID)
            var item = this.item[indexItem]
            var indexPatron = this.patrons.map(x => x.id).indexOf(patronID)
            var patron = this.patron[indexPatron]
            AXIOS.post('/return', {},
            {params: {
                item: item.id,
                patron: patron.id}})
            .then(response => {
                patron.borrowedItems.splice((patron.borrowedItems.indexOf(item)),1)
                this.itemID = ''
                this.patronID = ''
                this.errorMsg = ''
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorRegistration = errorMsg
            })
        },

        archiveItem: function(itemID, librarianID) {

        },

        damageItem: function(itemID, librarianID) {

        },

        createItem: function(itemID, itemType, itemName, itemAuthor, librarianID) {

        },

        deleteItem: function(itemID, librarianID) {

        }
    }
}