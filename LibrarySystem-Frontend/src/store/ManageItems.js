import axios from 'axios'
import e from 'express'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

  export default {
    name: 'manageItem',
    data () {
      return {
        itemID = '',
        itemType = '',
        itemName = '',
        itemAuthor = '',

        patronID = '',
        librarians: [],
        librarianID = '',

        response= [],
        errorMsg = '',
      }
    },

    created: function () {

    },
    methods: {
        borrowItem: function(itemID, patronID) {
            // sami service method requires the item name?
        },

        returnItem: function(itemID, patronID) {
            AXIOS.post('/return/'.concat('?itemId=', itemID, '&patronId=', patronID))
            .then(response => {
                this.itemID = ''
                this.patronID = ''
                this.errorMsg = ''
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },

        archiveItem: function(itemID, librarianID) {
            AXIOS.post('/archive/'.concat(itemID, '?headLibrarianID=', librarianID))
            .then(response => {
                this.itemID = ''
                this.librarianID = ''
                this.errorMsg = ''
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },

        damageItem: function(itemID, librarianID) {
            // missing damaged controller method
        },

        createItem: function(itemType, itemName, itemAuthor, librarianID) {
          if (itemType.localeCompare("Book")) {
            AXIOS.post('/createBook/'.concat(itemName, '?authorName=', itemAuthor, '?isArchived=', false))
            .then(response => {
              this.itemName = ''
              this.itemAuthor = ''
              this.librarianID = ''
            })
            .catch(e => {
            var errorMsg = e
            console.log(errorMsg)
            this.errorMsg = errorMsg
            })
          } else if (itemType.localeCompare("Album")) {
            AXIOS.post('/createAlbum/'.concat(itemName, '?artistName=', itemAuthor, '?isArchived=', false))
            .then(response => {
              this.itemName = ''
              this.itemAuthor = ''
              this.librarianID = ''
            })
            .catch(e => {
            var errorMsg = e
            console.log(errorMsg)
            this.errorMsg = errorMsg
            })
          } else if (itemType.localeCompare("Movie")) {
            AXIOS.post('/createMovie/'.concat(itemName, '?directorName=', itemAuthor, '?isArchived=', false))
            .then(response => {
              this.itemName = ''
              this.itemAuthor = ''
              this.librarianID = ''
            })
            .catch(e => {
            var errorMsg = e
            console.log(errorMsg)
            this.errorMsg = errorMsg
            })

          } else if (itemType.localeCompare("Journal")) {

          } else if (itemType.localeCompare("Newspaper")) {

          }
        },

        deleteItem: function(itemID, librarianID) {
            AXIOS.post('/items/discard'.concat('?itemId=',itemID,'&headLibrarianID=',librarianID))
            .then(response => {
                this.items.push(response.data)
                this.itemID = ''
                this.librarianID = ''
                this.errorMsg = ''
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        }

        // need a function to set a item as 'available'? I.e., isArchived = isBorrowed = isDamaged = false
    }
}