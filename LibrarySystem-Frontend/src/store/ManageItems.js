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

        itemDate:'',

        response: [],
        errorMsg: '',
      }
    },

    created: function () {
    },
    methods: {
        // service that allows a librarian to borrow an item for a patron
        borrowItem: function(itemID, itemName, patronID) {
            AXIOS.post('/borrow/'.concat(itemName,'/?itemId=', itemID, '&patronId=',patronID))
            .then(response => {
              this.itemName= ''
              this.itemID = ''
              this.patronID = ''
            })
            .catch(e => {
              var errorMsg = e
              console.log(errorMsg)
              this.errorMsg = errorMsg
            })
        },
        // service that allows a librarian to return an item for a patron
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
        // service that allows a librarian to set an item as archived
        archiveItem: function(itemID) {
            var librarianID = AXIOS.get('/onlineAccountLoggidInUser')
            AXIOS.post('/archive/'.concat('?itemID=', itemID, '&headLibrarianID=', librarianID))
            .then(response => {
                this.itemID = ''
                this.errorMsg = ''
                window.location.reload();
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },
        // service that allows a librarian to set an item as damaged
        damageItem: function(itemID) {
          var librarianID = AXIOS.get('/onlineAccountLoggidInUser')
            AXIOS.post('/items/setDamaged/'.concat('?itmID=', itemID, '&headLibrarianID=', librarianID))
            .then(response => {
              this.itemID = ''
              this.errorMsg = ''
              window.location.reload();
          })
          .catch(e => {
              var errorMsg = e
              console.log(errorMsg)
              this.errorMsg = errorMsg
          })
        },
        // Service that allows a librarian to create an item
        // Needs 'if statements' since different items require different parameters
        // Uses the built-in method .localeCompare() to compare the selected String from the dropdown menu 
        // and determine the item type
        createItem: function(itemType, itemName, itemAuthor, itemDate) {
          if (itemType.localeCompare("Book") == 0) {
            AXIOS.post('/createBook/'.concat(itemName, '/?authorName=', itemAuthor, '&isArchived=', false))
            .then(response => {
              this.itemName = ''
              this.itemAuthor = ''
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
            })
            .catch(e => {
            var errorMsg = e
            console.log(errorMsg)
            this.errorMsg = errorMsg
            })
          } else if (itemType.localeCompare("Journal") == 0) {
            AXIOS.post('/createJournal/'.concat(itemName, '/?journalDate=', itemDate))
            .then(response => {
              this.itemName = ''
              this.itemDate=''
            })
            .catch(e => {
            var errorMsg = e
            console.log(errorMsg)
            this.errorMsg = errorMsg
            })

          } else if (itemType.localeCompare("Newspaper") == 0) {
            AXIOS.post('/createNewspaper/'.concat(itemName, '/?newspaperDate=', itemDate))
            .then(response => {
              this.itemName = ''
              this.itemDate=''
            })
            .catch(e => {
            var errorMsg = e
            console.log(errorMsg)
            this.errorMsg = errorMsg
            })
          }
        },
        // service that allows a librarian to delete an item from the library
        deleteItem: function(itemID) {
          var librarianID = AXIOS.get('/onlineAccountLoggidInUser')
            AXIOS.delete('/items/discard/'.concat('?itemID=',itemID,'&headLibrarianID=',librarianID))
            .then(response => {
                this.itemID = ''
                this.errorMsg = ''
                window.location.reload();
            })
            .catch(e => {
                var errorMsg = e
                console.log(errorMsg)
                this.errorMsg = errorMsg
            })
        },
      // service that allows a librarian to remove an item from the archives/damages and allows it to be borrowed
        makeAvailable: function(itemID) {
          var librarianID = AXIOS.get('/onlineAccountLoggidInUser')
          AXIOS.post('/items/available/'.concat('?itemID=',itemID,'&headLibrarianID=',librarianID))
          .then(response => {
              this.itemID = ''
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