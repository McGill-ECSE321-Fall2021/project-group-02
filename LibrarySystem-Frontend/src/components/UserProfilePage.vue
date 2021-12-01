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
    <div class="user-profile">
      <div class="main">
        <div class="main-box">
          <div class = "profile-box">
            <img src="../pictures/user_profile/blank_profile_pic.png" class="rounded-circle" width="150" alt="not Found" onerror="../pictures/user_profile/blank_profile_pic.png">
            <div class="subpages">
              <div v-for="onlineAccount in onlineAccountLogged" :key="onlineAccount.accountId">
                <h3>{{ onlineAccount.firstName }} {{ onlineAccount.lastName }}</h3>
              </div>
              <router-link to="/userProfile"><a>Profile</a></router-link>
              <a><router-link to="/userProfile/borrowedItems">Borrowed Items</router-link></a>
              <a><router-link to="/" @click="signOutUser()">Sign out</router-link></a>
            </div>
          </div>

          <div class = "user-info">
            <div class="account-details-text">
              <h1>Account Details</h1>
            </div>
            <div class="user-info-body">
              <div class="row">
                <div class="column1">
                  <h5>Username: </h5>
                </div>
                <div v-for="onlineAccount in onlineAccountLogged" :key="onlineAccount.accountId">
                  <h5>{{ onlineAccount.username }} </h5>
                </div>
                <!--click on arrow to redirect to change username page-->
                <a href="#popup1" class="long-button"><i class="arrow right"></i></a>
              </div>
              <div class="row">
                <div class="column1">
                  <h5>Email: </h5>
                </div>
                <div v-for="onlineAccount in onlineAccountLogged" :key="onlineAccount.accountId">
                  <h5>{{ onlineAccount.email }} </h5>
                </div>
                <!--click on arrow to redirect to change email page-->
                <a href="#popup2" class="long-button"><i class="arrow right"></i></a>
              </div>
              <div class="row">
                <div class="column1">
                  <h5>balance: </h5>
                </div>
                <div v-for="onlineAccount in onlineAccountLogged" :key="onlineAccount.accountId">
                  <h5>${{ onlineAccount.balance }} </h5>
                </div>
              </div>
              <div class="row">
                <a href="#popup3" class="square-button">Change password</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div id="popup1" class="overlay">
	    <div class="popup">
	      <h2>Change username</h2>
		    <a class="close" href="#">&times;</a>
		    <div class="content">
          <div class="new-username form">
            <h3>new username: </h3>
            <input type="text" v-model="newUsername" required>
          </div>
          <div class="password form">
            <h3>password: </h3>
            <input type="text" v-model="password" required>
          </div>
          <button class="button" onClick="refreshPage()" @click="changeUsername(password, newUsername)">confirm</button>
		    </div>
        <span v-if="errorOnlineAccount" style="color:red">Error: {{errorOnlineAccount}} </span>
	    </div>
    </div>
    <div id="popup2" class="overlay">
	    <div class="popup">
	      <h2>Change email</h2>
		    <a class="close" href="#">&times;</a>
		  <div class="content">
			  <div class="new-email form">
          <h3>new email: </h3>
          <input type="text" v-model="newEmail" required>
        </div>
        <div class="password form">
          <h3>password: </h3>
          <input type="text" v-model="password" required>
        </div>
          <button class="button" onClick="refreshPage()" @click="changeEmail(password, newEmail)">confirm</button>
		    </div>
	    </div>
    </div>
    <div id="popup3" class="overlay">
	    <div class="popup">
	      <h2>Change password</h2>
		    <a class="close" href="#">&times;</a>
		  <div class="content">
        <div class="password form">
          <h3>password: </h3>
          <input type="text" v-model="password" required>
        </div>
			  <div class="new-password form">
          <h3>new password: </h3>
          <input type="text" v-model="newPassword" required>
        </div>
          <button class="button" onClick="refreshPage()" @click="changePassword(password, newPassword)">confirm</button>
		    </div>
	    </div>
    </div>
  </body>
</template>

<script src="../store/userProfile.js"></script>

<style>
  .header{
    width:100%;
    height: 130px;
    position: fixed;
    top: 0;
    left: 0;
    background:rgb(112, 1, 1);
    box-shadow: 5px 20px 50px #000;
    z-index: 2;
  }

  .main{
	width: 100%;
	height: 100%;
}

  .header a{
    color: white;
    text-decoration: none;
  }
  .header a:hover{
    color: black;
  }
    .btn{
    float:right;
    margin-right: 5px;
    margin-top: 75px;
    color:white;
    background:rgba(0, 0, 0, 0.8);
    padding:10px 20px;
    font-size:12px;
    text-decoration:none;
    letter-spacing:2px;
    text-transform:uppercase;
  }
  .header h2{
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
  body{
    background-image: url("../assets/library.jpg");
  }
  .user-profile{
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
  .main{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  .main-box{
    background-color: rgba(0, 0, 0, 0.6);
    margin: 10px;
    margin-top: 90px;
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
  .subpages:hover {
    text-decoration: none;
  }
  .user-info{
    background-color: white;
    padding: 10px;
    float: right;
    width: 50em;
    display: block;
  }
  .row{
    padding: 10px;
  }
  .column1{
    padding-left: 10px;
    padding-right: 15px;
  }
  .username{
    display: inline;
  }
  .email{
    display: inline;
  }
  .long-button {
    text-decoration: none;
  }
  .button {
    margin-top: 1em;
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
  .square-button{
    float:right;
    margin-left: 1em;
    color:white;
    background:rgb(160, 1, 1);
    padding:10px 20px;
    font-size:14px;
    text-decoration:none;
    letter-spacing:2px;
    border-radius: 5px;
  }
  .square-button:hover{
    background:rgb(114, 1, 1);
    text-decoration: none;
    color: white;
  }
  .arrow {
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  margin-left: 10em;
  }
  .right {
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  }
  .overlay {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.7);
  transition: opacity 500ms;
  visibility: hidden;
  opacity: 0;
}
.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 10% auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  position: relative;
  transition: all 5s ease-in-out;
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 30px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.popup .close:hover {
  color: #06D85F;
}
.popup .content {
  max-height: 30%;
  overflow: auto;
}

@media screen and (max-width: 700px){
  .box{
    width: 70%;
  }
  .popup{
    width: 70%;
  }
}
</style>