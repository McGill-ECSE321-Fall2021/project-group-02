<template>
  <body>
    <div class="borrowed-items-page">
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

          <div class = "borrowed-items">
            <div class="borrowed-items-text">
              <h1>Borrowed Items</h1>
            </div>
            
            <div class="item">
              <div class="image">
                <img src="../pictures/user_profile/book_example.jpg" width="150" alt="not Found" onerror="../pictures/user_profile/blank_book.png">
              </div>

              <div class="text">
                <div class="title">
                  Title/Name
                </div>
                <div class="author">
                  Author/Artist
                </div>
                <div class="itemid">
                  1234
                </div>
                <div class="item-type">
                  Item type: 
                  <div class="type">
                    book
                  </div>
                </div>
                <div class="return-date">
                  Return by: 
                  <div class="date">
                    12/10/2021
                  </div>
                </div>
              </div>
            </div>

            <div class="item">
              <div class="image">
                <img src="../pictures/user_profile/book_example.jpg" width="150" alt="not Found" onerror="../pictures/user_profile/blank_book.png">
              </div>

              <div class="text">
                <div class="title">
                  Title/Name
                </div>
                <div class="author">
                  Author/Artist
                </div>
                <div class="itemid">
                  1234
                </div>
                <div class="item-type">
                  Item type: 
                  <div class="type">
                    book
                  </div>
                </div>
                <div class="return-date">
                  Return by: 
                  <div class="date">
                    12/10/2021
                  </div>
                </div>
              </div>
            </div>

            <div class="item">
              <div class="image">
                <img src="../pictures/user_profile/book_example.jpg" width="150" alt="not Found" onerror="../pictures/user_profile/blank_book.png">
              </div>

              <div class="text">
                <div class="title">
                  Title/Name
                </div>
                <div class="author">
                  Author/Artist
                </div>
                <div class="itemid">
                  1234
                </div>
                <div class="item-type">
                  Item type: 
                  <div class="type">
                    book
                  </div>
                </div>
                <div class="return-date">
                  Return by: 
                  <div class="date">
                    12/10/2021
                  </div>
                </div>
              </div>
            </div>
            <!--
            <div class="footer">
              <a href="#" class="arrow-button"><i class="arrow left"></i></a>
              page-number-placeholder
              <a href="#" class="arrow-button"><i class="arrow right"></i></a>
            </div>
            -->
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<style>
  body{
    background-image: url("../assets/library.jpg");
  }
  .main{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  .borrowed-items-page{
    display: flex;
    justify-content: center;
    background-color: rgba(255, 255, 255, 0.6);
    margin-left: 10%;
    margin-right: 10%;
    padding-bottom: 100px;
    padding-left: 50px;
    padding-right: 50px;
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