<template>
  <div class="welcomepage">
      <div class="header">
				  <h2><router-link to="/">Montreal Library</router-link></h2>
        <a class="btn"><router-link to="/signup">Sign Up/Log In</router-link></a>
        <a class="btn"><router-link to="/items">Items Information</router-link></a>
        <a class="btn"><router-link to="/">Home</router-link></a>
		  </div>
    
    <h1>Welcome To The Montreal Library Website!</h1>
    
    <a class="btn"><router-link to="/">Home</router-link></a>
    <a class="btn"><router-link to="/signup">Sign Up/Log In</router-link></a>
    <a class="btn">Items Information</a>
    <a class="btn"><router-link to="/manageLibrarySchedule">Manage Library Schedule</router-link></a>
    <a class="btn"><router-link to="/createPatron">Create Patron</router-link></a>
    
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
  padding:75px;
  text-align: center;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  
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

h2{
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

h1{
  color:white;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-weight:350;
  width: 900px;
  margin: 0 auto;
  margin-top: 300px;
  background:rgb(112, 1, 1);
  padding: 10px 20px;
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



.btn:hover{
  background:#fff;
}

.footer{
  font-size:8px;
  color:#fff;
  clear:both;
  display:block;
  letter-spacing:5px;
  border:1px solid #fff;
  padding:5px;
  text-decoration:none;
  width:210px;
  margin:auto;
  margin-top:400px;
}
</style>