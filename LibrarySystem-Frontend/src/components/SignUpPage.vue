<template>
    <div class="signuppage">
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
<body>
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">
			<div class="signup1">
				<form>
					<label for="chk" aria-hidden="true">Sign Up As A Nonexisting User</label>
					<input type="text" v-model="firstName" placeholder="First Name" required="">
					<input type="text" v-model="lastName" placeholder="Last Name" required="">	
                    <input type="text" v-model="address" placeholder="Address" required="">
					<input type="text" v-model="city" placeholder="City" required="">
					<input type="text" v-model="username" placeholder="Username" required="">
                    <input type="password" v-model="password" placeholder="Password" required="">
					<input type="email" v-model="email" placeholder="Email" required="">
					<button @click="createAccountNewUser(username, password, email, address, city, firstName, lastName)">Sign up</button>
					<p>
					<span v-if="errorMsg" style="color:red; margin-left: -160px;"> Error: {{ errorMsg }} </span>
					</p>

				</form>
			</div>

			<div class="signup2">
				<form>
					<label for="chk" aria-hidden="true">Sign Up As An Existing User</label>
                    <input type="text" placeholder="userId" v-model="userId" required="">
					<input type="text" placeholder="Username" v-model="usernameExisting" required="">
					<input type="password" placeholder="Password" v-model="passwordExisting" required="">
					<input type="email" placeholder="Email" v-model="emailExisting" required="">
					<button @click="createAccountExistingUser(usernameExisting, passwordExisting, emailExisting, userId)">Sign up</button>
					<p>
					<span v-if="errorMsg" style="color:red; margin-left: -160px;"> Error: {{ errorMsg }} </span>
					</p>
				</form>
			</div>

			<div class="login">
				<form>
					<label for="chk" aria-hidden="true">Login</label>
					<input type="txt" placeholder="Username" v-model="usernameLogin" required="">
					<input type="password" placeholder="Password" v-model="passwordLogin" required="">
					<button @click="logInUser(usernameLogin, passwordLogin)">Login</button>
					<p>
					<span v-if="errorMsg" style="color:red; margin-left: -160px;"> Error: {{ errorMsg }} </span>
					</p>
				</form>
			</div>

	</div>
</body>
    </div>
</template>

<script src="../store/SignUp.js">
</script>

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
.btn{
  float:right;
  margin: 75px 5px;
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
	margin-top: 138px;
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
#chk:checked ~ .signup1 label{
	transform: scale(.6);
}

#chk:checked ~ .signup2 label{
	transform: scale(.6);
}

</style>