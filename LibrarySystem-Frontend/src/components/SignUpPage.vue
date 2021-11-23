<template>
    <div class="signuppage">
        <div class="header">
		<h2><router-link to="/">Montreal Library</router-link></h2>
        <a class="btn"><router-link to="/signup">Sign Up/Log In</router-link></a>
        <a class="btn"><router-link to="/items">Items Information</router-link></a>
        <a class="btn"><router-link to="/">Home</router-link></a>
		  </div>
<body>
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">

			<div class="signup1">
				<form>
					<label for="chk" aria-hidden="true">Sign Up As A Nonexisting User</label>
					<input type="text" name="firstName" placeholder="First Name" required="">
					<input type="text" name="lastName" placeholder="Last Name" required="">	
                    <input type="text" name="username" placeholder="Username" required="">
                    <input type="text" name="city" placeholder="City" required="">
                    <input type="text" name="address" placeholder="Address" required="">
                    <input type="email" name="email" placeholder="Email" required="">
					<input type="password" name="pswd" placeholder="Password" required="">
					<button>Sign up</button>
				</form>
				
			</div>

			<div class="signup2">
				<form>
					<label for="chk" aria-hidden="true">Sign Up As An Existing User</label>
                    <input type="text" name="username" placeholder="Username" required="">
					<input type="password" name="pswd" placeholder="Password" required="">
					<input type="text" name="userId" placeholder="UserId" required="">
					<button>Sign up</button>
				</form>
			</div>

			<div class="login">
				<form>
					<label for="chk" aria-hidden="true">Login</label>
					<input type="txt" name="username" placeholder="Username" required="">
					<input type="password" name="pswd" placeholder="Password" required="">
					<button>Login</button>
				</form>
			</div>
	</div>
</body>
    </div>
</template>
<script>
</script>
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
.main{
	width: 530px;
	height: 600px;
	overflow: hidden;
	border-radius: 10px;
	background:rgba(0, 0, 0, 0.8);
}
#chk{
	display: none;
	
	
}

.signup2{
	left: 160px;
	bottom:618px;
	position: relative;
	width:50%;
	height: 50%;
}


.signup1{
	right: 100px;
	position: relative;
	width:100%;
	height: 100%;
}

.signup2 label{
	
	font-size: 1.2em;
	margin: 20px 140px;
	width: 210px;
}

.signup1 label{
	
	font-size: 1.2em;
	margin: 20px 130px;
	width: 210px;
}
label{
	text-align:center;
	color: #fff;
	justify-content: center;
	display: flex;
	margin: 60px;
	font-weight: bold;
	cursor: pointer;
	transition: .5s ease-in-out;
	
}
input{
	width: 210px;
	height: 20px;
	background: white;
	justify-content: center;
	display: flex;
	margin: 20px 140px;
	padding: 10px;
	border: none;
	outline: none;
	border-radius: 5px;
}
button{
	width: 210px;
	height: 40px;
	margin: 10px 140px;
	justify-content: center;
	display: block;
	color: black;
	background: white;
    
	font-size: 1em;
	margin-top: 20px;
	outline: none;
	border: none;
	border-radius: 5px;
	transition: .2s ease-in;
	cursor: pointer;
}

.signup2 button{
	margin-top: 180px;
}

.login button{
	margin: 10px auto;
}

button:hover{
	background: black;
    color:white;
}
.login{
	margin-top: -240px;
	height: 560px;
	background: white;
	border-radius: 60% / 10%;
	transform: translateY(-270px);
	transition: .8s ease-in-out;
}
.login label{
	color: black;
	font-size: 2.3em;
	transform: scale(.6);
}

#chk:checked ~ .login{
	transform: translateY(-680px);
}
#chk:checked ~ .login label{
	transform: scale(1);	
}
#chk:checked ~ .signup label{
	transform: scale(.6);
}

</style>