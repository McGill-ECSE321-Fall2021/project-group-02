<template>
  <body>
    <div class="user-profile">
      <div class="main">
        <div class="topbar">
          <a href="">Home</a>
        </div>

        <div class="main-box">
          <div class = "profile-box">
            <img src="../pictures/user_profile/blank_profile_pic.png" class="rounded-circle" width="150" alt="not Found" onerror="../pictures/user_profile/blank_profile_pic.png">
            <div class="subpages">
              <h3>Template Name</h3>
              <a><router-link to="/userProfile">Profile</router-link></a>
              <a><router-link to="/userProfile/borrowedItems">Borrowed Items</router-link></a>
              <a href="">Sign out</a>
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
                <div class="username">
                  <h5>Template username</h5>
                </div>
                <!--click on arrow to redirect to change username page-->
                <a href="#popup1" class="long-button"><i class="arrow right"></i></a>
              </div>
              <div class="row">
                <div class="column1">
                  <h5>Email: </h5>
                </div>
                <div class="email">
                  <h5>template@gmail.com</h5>
                </div>
                <!--click on arrow to redirect to change email page-->
                <a href="#popup2" class="long-button"><i class="arrow right"></i></a>
              </div>
              <div class="row">
                <a href="#popup3" class="square-button">Change password</a>
                <a href="#popup4" class="square-button">Delete account</a>
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
          <input type="text" required>
        </div>
        <div class="password form">
          <h3>password: </h3>
          <input type="text" required>
        </div>
          <button class="button">confirm</button>
		    </div>
	    </div>
    </div>
    <div id="popup2" class="overlay">
	    <div class="popup">
	      <h2>Change email</h2>
		    <a class="close" href="#">&times;</a>
		  <div class="content">
			  <div class="new-email form">
          <h3>new email: </h3>
          <input type="text" required>
        </div>
        <div class="password form">
          <h3>password: </h3>
          <input type="text" required>
        </div>
          <button class="button">confirm</button>
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
          <input type="text" required>
        </div>
			  <div class="new-password form">
          <h3>new password: </h3>
          <input type="text" required>
        </div>
          <button class="button">confirm</button>
		    </div>
	    </div>
    </div>
    <div id="popup4" class="overlay">
	    <div class="popup">
	      <h2>Delete account</h2>
		    <a class="close" href="#">&times;</a>
		  <div class="content">
        <div class="password form">
          <h3>password: </h3>
          <input type="text" required>
        </div>
          <button class="button">confirm</button>
		    </div>
	    </div>
    </div>
  </body>
</template>

<style>
  body{
    background-image: url("../assets/library.jpg");
  }
  .user-profile{
    display: flex;
    justify-content: center;
    background-color: rgba(255, 255, 255, 0.6);
    margin-left: 10%;
    margin-right: 10%;
    padding-bottom: 100px;
    padding-left: 50px;
    padding-right: 50px;
  }
  .main{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  .topbar{
    background-color: rgb(112, 1, 1);
    color: white;
    text-align: right;
    padding: 20px;
    margin: 10px;
  }
  .topbar a{
    color: white;
    padding: 20px 20px;
    font-size: 20px;
  }
  .main-box{
    background-color: lightgray;
    margin: 10px;
  }
  .profile-box{
    float: left;
    padding: 15px;
    background-color: drgb(112, 1, 1);
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
    margin: 5px;
    background-color: darkcyan;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px; 
  }
  .button:hover{
    background-color: green;
  }
  .square-button{
    margin: 5px;
    background-color: darkcyan;
    border: none;
    color: white;
    padding: 15px 32px;
    text-decoration: none;
  }
  .square-button:hover{
    background-color: green;
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
  margin: 70px auto;
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