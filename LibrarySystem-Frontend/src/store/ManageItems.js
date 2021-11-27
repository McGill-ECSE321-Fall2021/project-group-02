import axios from 'axios'
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
        itemID: '',
        itemType: '',
        itemName: '',
        itemAuthor: '',

        patronID: '',
        librarians: [],
        librarianID: '',

        response: [],
        errorMsg: '',
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
                window.location.reload();
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },

        archiveItem: function(itemID, librarianID) {
            AXIOS.post('/archive/'.concat('?itemID=', itemID, '&headLibrarianID=', librarianID))
            .then(response => {
                this.itemID = ''
                this.librarianID = ''
                this.errorMsg = ''
                window.location.reload();
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },
      
        // not tested yet
        damageItem: function(itemID, librarianID) {
            AXIOS.post('/items/setDamaged/'.concat('?itmID=', itemID, '&headLibrarianID=', librarianID))
            .then(response => {
              this.itemID = ''
              this.librarianID = ''
              this.errorMsg = ''
              window.location.reload();
          })
          .catch(e => {
              var errorMsg = e
              console.log(errorMsg)
              this.errorMsg = errorMsg
          })
        },

        createItem: function(itemType, itemName, itemAuthor, librarianID) {
          if (itemType.localeCompare("Book") == 0) {
            AXIOS.post('/createBook/'.concat(itemName, '/?authorName=', itemAuthor, '&isArchived=', false))
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
          } else if (itemType.localeCompare("Album") == 0) {
            AXIOS.post('/createAlbum/'.concat(itemName, '/?artistName=', itemAuthor, '&isArchived=', false))
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
          } else if (itemType.localeCompare("Movie") == 0) {
            AXIOS.post('/createMovie/'.concat(itemName, '/?directorName=', itemAuthor, '&isArchived=', false))
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

            // not sure?
          } else if (itemType.localeCompare("Journal") == 0) {

          } else if (itemType.localeCompare("Newspaper") == 0) {

          }
        },

        deleteItem: function(itemID, librarianID) {
            AXIOS.delete('/items/discard/'.concat('?itemID=',itemID,'&headLibrarianID=',librarianID))
            .then(response => {
                this.itemID = ''
                this.librarianID = ''
                this.errorMsg = ''
                window.location.reload();
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },

        // not tested
        makeAvailable: function(itemID, librarianID) {
          AXIOS.post('/items/available/'.concat('?itemID=',itemID,'&headLibrarianID=',librarianID))
          .then(response => {
              this.itemID = ''
              this.librarianID = ''
              this.errorMsg = ''
              window.location.reload();
          })
          .catch(e => {
              var errorMsg = e
              console.log(errorMsg)
              this.errorMsg = errorMsg
          })
        }
    }
}