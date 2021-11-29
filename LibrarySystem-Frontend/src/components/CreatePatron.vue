<template>
    <div class="createpatronpage">
		<body>
			<div class="header">
			<router-link to="/"><h1>Montreal Library</h1></router-link>
					<router-link to="/signup"><a class="btn">Sign Up/Log In</a></router-link>
					<router-link to="/items"><a class="btn">Items Information</a></router-link>
					<router-link to="/"><a class="btn">Home</a></router-link>
			</div>
				<div class="createPatron">
					<form>
						<h2> Create Patron </h2>
						<input type="text" v-model="firstName" placeholder="First Name" required="">
						<input type="text" v-model="lastName" placeholder="Last Name" required="">
						<input type="text" v-model="city" placeholder="City" required="">
						<input type="text" v-model="address" placeholder="Address" required="">
						<button @click="createPatron(firstName, lastName, city, address)">Create Patron</button>
						<p><span v-if="errorMsg" style="color:red">Error: {{errorMsg}} </span></p>
					</form>
				</div>
		</body>
    </div>
</template>

<script>
</script>

<style>
/* Navigation Bar */
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
h1{
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

/* Body */
body{
	margin: 60px 0px;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: left;
	min-height: 100vh;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-image: url("../assets/library.jpg");
}
h2{
	color: white;
	font-weight: bold;
}
.createPatron{
	background:rgba(0, 0, 0, 0.8);
	overflow: hidden;
	border-radius: 10px 10px 10px 10px;
	padding: 25px;
	width: 500px;
	height: 100%;
	text-align: center;
	justify-content: center;
}
form{
	display: flex;
	flex-direction: column;
	gap: 15px;
}
input{
	justify-content: center;	
	border: none;
	outline: none;
	border-radius: 5px;
	padding: 2px;
}
button{
	justify-content: center;
	display: block;
	color: black;
	background: white;
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