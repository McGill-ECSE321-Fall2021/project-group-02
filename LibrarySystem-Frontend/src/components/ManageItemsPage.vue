<template>
  <div class="manageitemspage">
    <body>
      <div class="header">
              <h1><router-link to="/">Montreal Library</router-link></h1>
            <a class="btn"><router-link to="/signup">Sign Up/Log In</router-link></a>
            <a class="btn"><router-link to="/items">Items Information</router-link></a>
            <a class="btn"><router-link to="/">Home</router-link></a>
		  </div>
      
      <div class="enterItem">
        <form>
					<label for="chk" aria-hidden="true">Manage Item</label>
					<input type="text" name="itemID" placeholder="Enter item ID" required="">
          <button>Enter</button>
        </form>
      </div>

      <div class="manageItem">
        <div class="itemInfo">
          <!-- input the ID, name, and author -->
          <p>Put item info here</p>
        </div>
        <div class="itemButtons">
          <button>Borrow Item</button>
          <button>Return Item</button>
          <button>Archive Item</button>
          <button>Damage Item</button>
          <button>Add a New Item</button>
          <button>Discard Item</button>
        </div>  
      </div>
    </body>
  </div>
</template>


<script>
</script>


<style>
* {
	-webkit-transition-property: all;
	-webkit-transition-duration: .2s;
  -moz-transition-timing-function: cubic-bezier(100,50,21,6);
	-moz-transition-property: all;
  -moz-transition-timing-function: cubic-bezier(100,50,21,6);
}

body{
	padding: 0;
	display: flex;
  flex-direction: column;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-image: url("../assets/library.jpg");
}

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

h1{
	text-align: left;
	color:white;
	font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	font-weight:350;
	position: fixed;
	top: 0;
	left: 0;
	
	width: 400px;
	background:rgb(112, 1, 1);
	padding: 10px 10px;
}

.btn{
  float:right;
  margin: 45px 5px;
  color:white;
  background:rgba(0, 0, 0, 0.8);
  padding:10px 20px;
  font-size:12px;
  text-decoration:none;
  letter-spacing:2px;
  text-transform:uppercase;
}
.btn:hover {
  background:#fff;
}

.enterItem{
	position: relative;
  bottom: 100px;
	width:100%;
	height: 100%;
	background:rgba(0, 0, 0, 0.65);
	overflow: none;
	border-radius: 10px;
	background-image: none;
  padding: 15px;
  display: flex;
  align-items: center;
}

.enterItem label{
	font-size: 2em;
	margin: 20px 140px;
	width: 400px;
  height: 50px;
}

label{
  background: rgb(112, 1, 1);
	text-align:center;
	color: #fff;
	justify-content: center;
	display: flex;
}

input{
	background: white;
	padding: 10px;
	border: none;
	outline: none;
	border-radius: 5px;
  margin-left: 30%;
  margin-bottom: 3%;
}

.manageItem {
	background:rgba(0, 0, 0, 0.65);
	border-radius: 10px;
	background-image: none;
  padding: 5px;
  height: 150px;
}

p {
  text-align: center;
  color: #fff;
  margin-top: 20px;
}

button{
	color: black;
	background: white;
	font-size: 1em;
	font-weight: bold;
  margin-left: 0;
	outline: none;
	border: none;
	border-radius: 5px;
	transition: .2s ease-in;
	cursor: pointer;
}
button:hover{
	background: black;
    color:white;
}

</style>

