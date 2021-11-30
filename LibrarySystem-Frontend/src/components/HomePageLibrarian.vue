<template>
  <div class="welcomepage">
      <div class="header">
				 <router-link to="/homePageLibrarian"><h2>Montreal Library</h2></router-link>
            <router-link to="/"><a class="btn">Manage Schedule</a></router-link>
            <router-link to="/schedules"><a class="btn">View Librarian Schedule</a></router-link>
            <router-link to="/manageitems"><a class="btn">Manage Items</a></router-link>
				    <router-link to="/userProfileLibrarian"><a class="btn">User Profile</a></router-link>
        		<router-link to="/items"><a class="btn">Items Information</a></router-link>
        		<router-link to="/homePageLibrarian"><a class="btn">Home</a></router-link>
		  </div>
    
    <h1>Welcome To The Montreal Library Website!</h1>
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
.header a{
	color: white;
}
.header a:hover{
	color: black;
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