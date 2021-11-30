<template>
  <body>
    <div class="header">
      <template v-if="getTypeOfUser().includes('Patron')">
        <router-link to="/homeAfterLogin"><h2>Montreal Library</h2></router-link>
        <router-link to="/userProfile"><a class="btn">User Profile</a></router-link>
        <router-link to="/items"><a class="btn">Items Information</a></router-link>
        <router-link to="/homeAfterLogin"><a class="btn">Home</a></router-link>
      </template>
      <template v-else-if="getTypeOfUser().includes('HeadLibrarian')">
        <router-link to="/homePageHeadLibrarian"><h2>Montreal Library</h2></router-link>
        <router-link to="/librarians"><a class="btn">Manage Employment</a></router-link>
        <router-link to="/manageLibrarySchedule"><a class="btn">Manage Library Schedule</a></router-link>
        <router-link to="/schedules"><a class="btn">View Librarian Schedule</a></router-link>
        <router-link to="/manageitems"><a class="btn">Manage Items</a></router-link>
        <router-link to="/createPatron"><a class="btn">Create Patron</a></router-link>
        <router-link to="/userProfileLibrarian"><a class="btn">User Profile</a></router-link>
        <router-link to="/items"><a class="btn">Items Information</a></router-link>
        <router-link to="/homePageHeadLibrarian"><a class="btn">Home</a></router-link>
      </template>
      <template v-else-if="getTypeOfUser().includes('Librarian')">
        <router-link to="/homePageLibrarian"><h2>Montreal Library</h2></router-link>
        <router-link to="/librarians"><a class="btn">Manage Employment</a></router-link>
        <router-link to="/manageLibrarySchedule"><a class="btn">Manage Library Schedule</a></router-link>
        <router-link to="/schedules"><a class="btn">View Librarian Schedule</a></router-link>
        <router-link to="/manageitems"><a class="btn">Manage Items</a></router-link>
        <router-link to="/createPatron"><a class="btn">Create Patron</a></router-link>
        <router-link to="/userProfileLibrarian"><a class="btn">User Profile</a></router-link>
        <router-link to="/items"><a class="btn">Items Information</a></router-link>
        <router-link to="/homePageLibrarian"><a class="btn">Home</a></router-link>
      </template>
      <template v-else>
        <router-link to="/"><h2>Montreal Library</h2></router-link>
            <router-link to="/signup"><a class="btn">Sign Up/Log In</a></router-link>
        <router-link to="/items"><a class="btn">Items Information</a></router-link>
        <router-link to="/"><a class="btn">Home</a></router-link>
      </template>
    </div>
    <div class="borrowed-items-page">
      <div class="main">

        <div class="main-box">
          <div class = "profile-box">
            <img src="../pictures/user_profile/blank_profile_pic.png" class="rounded-circle" width="150" alt="not Found" onerror="../pictures/user_profile/blank_profile_pic.png">
            <div class="subpages">
              <div v-for="onlineAccount in onlineAccountLogged" :key="onlineAccount.accountId">
                <h3>{{ onlineAccount.firstName }} {{ onlineAccount.lastName }}</h3>
              </div>
              <a><router-link to="/userProfile">Profile</router-link></a>
              <a><router-link to="/userProfile/borrowedItems">Borrowed Items</router-link></a>
              <a><router-link to="/" @click="signOutUser()">Sign out</router-link></a>
            </div>
          </div>

          <div class = "borrowed-items">
            <div class="borrowed-items-text">
              <h1>Borrowed Items</h1>
            </div>
            <a class = "button" @click="getBorrowedItems()">view items</a>
              <table>
                  <tr></tr>
                  <tr><th colspan="10">Books</th></tr>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Title</th>
                    <th scope="col">Author</th>
                  </tr>
                  <tr v-for="book in books" :key="book.id">
                      <td>{{ book.id }}</td>
                      <td>{{ book.title }}</td>
                      <td>{{ book.author }}</td>
                  </tr>
                  <tr></tr>
                  <tr><th colspan="10">Albums</th></tr>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Title</th>
                    <th scope="col">Artist</th>
                  </tr>
                  <tr v-for="album in albums" :key="album.id">
                    <td>{{ album.id }}</td>
                    <td>{{ album.title }}</td>
                    <td>{{ album.artist }}</td>
                  </tr>
                  <tr></tr>
                  <tr><th colspan="10">Movies</th></tr>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Title</th>
                    <th scope="col">Director</th>
                  </tr>
                  <tr v-for="movie in movies" :key="movie.id">
                    <td>{{ movie.id }}</td>
                    <td>{{ movie.title }}</td>
                    <td>{{ movie.director }}</td>
                  </tr>
              </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script src="../store/BorrowedItem.js"></script>


<style>
  .header{
    width:100%;
    height: 100px;
    position: fixed;
    top: 0;
    left: 0;
    background:rgb(112, 1, 1);
    box-shadow: 5px 20px 50px #000;
    z-index: 2;
  }

  .header a{
    color: white;
    text-decoration: none;
  }
  .header a:hover{
    color: black;
  }
  .button {
    display: flex;
    justify-content: center;
    color:white;
    background:rgb(160, 1, 1);
    padding:10px 20px;
    font-size:14px;
    text-decoration:none;
    letter-spacing:2px;
    border-radius: 5px;
  }
  .button:hover{
    background:rgb(114, 1, 1);
    text-decoration: none;
    color: white;
  }
    .btn{
    float:right;
    margin-right: 5px;
    color:white;
    background:rgba(0, 0, 0, 0.8);
    padding:10px 20px;
    font-size:12px;
    text-decoration:none;
    letter-spacing:2px;
    text-transform:uppercase;
  }
  .header-h2{
    text-align: left;
    color:white;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-weight:350;
    position: fixed;
    top: 0;
    left: 0;
    
    width: 250px;
    background:rgb(112, 1, 1);
    padding: 10px 10px;
  }
  .btn:hover{
    background:#fff;
  }
  table {
	width: 100%;
	border-collapse:separate;
	border-radius: 5px;
	
}
td{
	height: 10px;
	overflow: hidden;
}
th{
	font-weight: 500;
}
th:nth-child(1) {
	width: 10%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(2) {
	width: 50%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(3) {
	width: 40%;
	background: #ddd;
	padding-left: 5px;
}
  body{
    background-image: url("../assets/library.jpg");
  }
  .main{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  .borrowed-items-page{
    display: flex;
    justify-content: center;
    background-color: rgba(0, 0, 0, 0.6);
    margin-top: 5%;
    margin-left: 10%;
    margin-right: 10%;
    padding-bottom: 100px;
    padding-left: 50px;
    padding-right: 50px;
  }
  .main-box{
    background-color: rgba(0, 0, 0, 0.6);
    margin: 10px;
  }
  .profile-box{
    float: left;
    padding: 15px;
    background-color: rgb(112, 1, 1);
    color: white;
    height: 100%;
    text-align: center;
    width: 25em;
    margin-right: 5em;
  }
  .profile-box a{
    display: block;
    color: white;
    padding: 10px;
    font-size: 20px;
  }
  .borrowed-items-text{
    padding: 5px;
    padding-left: 30px;
    width: 70%;
    display: block;
  }
  .borrowed-items{
    background-color: darkgray;
    padding: 10px;
    float: right;
    width: 50em;
    display: block;
  }
  .item{
    padding: 5px;
    border-top: 2px solid black;
    float: right;
    width: 95%;
    display: block;
  }
  .type{
    display: inline;
  }
  .date{
    display: inline;
  }
  .arrow {
    border: solid black;
    border-width: 0 3px 3px 0;
    display: inline-block;
    padding: 3px;
    margin-left: 5px;
  }
  .right {
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  }
  .left {
    transform: rotate(135deg);
    -webkit-transform: rotate(135deg);
  }
  .footer {
    position: flex;
    color: black;
    text-align: center;
    bottom: 30px;
  }
  ::-webkit-scrollbar {
    width: 10px;
  }

  /* Track */
  ::-webkit-scrollbar-track {
    background: #f1f1f1; 
  }
  
  /* Handle */
  ::-webkit-scrollbar-thumb {
    background: #888; 
  }

  /* Handle on hover */
  ::-webkit-scrollbar-thumb:hover {
    background: #555; 
  }
</style>