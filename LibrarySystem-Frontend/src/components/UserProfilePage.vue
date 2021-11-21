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
              <a href="">Profile</a>
              <a href="">Borrowed Items</a>
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
                <a href="#" class="long-button"><i class="arrow right"></i></a>
              </div>
              <div class="row">
                <div class="column1">
                  <h5>Email: </h5>
                </div>
                <div class="email">
                  <h5>template@gmail.com</h5>
                </div>
                <!--click on arrow to redirect to change email page-->
                <a href="#" class="long-button"><i class="arrow right"></i></a>
              </div>
              <div class="row">
                <button class="button">Change password</button>
                <button class="button">Delete account</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<style>
  body{
    background-color: lightgray;
  }
  .main{
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
  }
  .topbar{
    background-color: darkcyan;
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
    background-color: darkslategray;
    color: white;
    height: 100%;
    text-align: center;
    width: 25%;
  }
  .profile-box a{
    display: block;
    color: white;
    padding: 10px;
    font-size: 20px;
  }
  .user-info{
    background-color: white;
    padding: 10px;
    float: right;
    width: 73%;
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
</style>