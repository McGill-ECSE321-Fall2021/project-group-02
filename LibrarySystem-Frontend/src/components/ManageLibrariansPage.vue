<template>
	<body>
		<div class="viewlibrarianspage">
			<div class="header">
				<router-link to="/"><h2>Montreal Library</h2></router-link>
				<router-link to="/signup"><a class="btn">Sign Up/Log In</a></router-link>
        		<router-link to="/items"><a class="btn">Items Information</a></router-link>
        		<router-link to="/"><a class="btn">Home</a></router-link>
			</div>
			<body>
				<div class=librarians>
					<h3>List of Librarians</h3>
					<form>
						<input class="search" v-model="librarianName" placeholder="Search librarian">
						 <button  @click="searchLibrarian(librarianName)">Search</button>
						<div class="dropdown">
						<button class="dropdownbutton">Sort By</button>
							<div class="dropdown-content">
								<button @click="sortLibrarian('fAZ')">First Name (A to Z)</button>
								<button @click="sortLibrarian('fZA')"> First Name (Z to A)</button>
								<button @click="sortLibrarian('lAZ')">Last Name (A to Z)</button>
								<button @click="sortLibrarian('lZA')">Last Name (Z to A)</button>
								<button @click="sortLibrarian('12')">ID (Low to High)</button>
								<button @click="sortLibrarian('21')">ID (High to Low)</button>
							</div>
						</div>
						<div id="newlibrarian">
						<table>
						<tr>
							<td>
								<td>
								<input class="text" v-model="firstName" placeholder="First Name">
								<input class="text" v-model="lastName" placeholder="Last Name">
								<input class="address" v-model="address" placeholder="Address">
								<input class="text" v-model="city" placeholder="City">
								<input class="text" v-model="id" placeholder="Validation Token">
      							  <button v-bind:disabled="!firstName || !lastName || !address || !city || !id" @click="createLibrarian(firstName,lastName,address,city,id)">Create librarian</button>
								</td>
						</tr>
						</table>
						<table>
						<tr>
							<td>
								<td>
								<input class="text" v-model="lid" placeholder="Librarian ID">
								<input class="text" v-model="hlid" placeholder="Validation Token">
      							<button v-bind:disabled="!lid || !hlid" @click="deleteLibrarian(lid,hlid)">Delete librarian</button>
								</td>
						</tr>
						</table>
						<p>
						 <span v-if="errorLibrarian" style="color:red">{{errorLibrarian}} </span> 
						</p>
					</div>				
					</form>
					<div class=lst>
						<table>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Address</th>
								<th>City</th>
								<th>ID</th>
							</tr>
							<tr v-for="librarian in librarians" :key="librarian.city">
								 <td>{{ librarian.firstName }}</td>
					            <td>{{ librarian.lastName }}</td>
								<td>{{ librarian.address }}</td>
								<td>{{ librarian.city }}</td>
								<td>{{ librarian.id }}</td>
							</tr>
						</table>
					</div>
				</div>
			</body>
		</div>
	</body>
</template>

<script src="../store/ManageLibrarians.js">

</script>

<style>
body{
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: left;
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
h3{
	text-align: left;
	color:white;
	margin-top: 10px;
	font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	font-size: 45px;
	font-weight:350;
	
	background:rgb(112, 1, 1);
	padding: 10px 10px;
}
table {
	width: 100%;
	border-collapse:separate;
	border-radius: 5px;
	
}
th:nth-child(1) {
	width: 25%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(2) {
	width: 25%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(3) {
	width: 25%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(4) {
	width: 15%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(5) {
	width: 10%;
	background: #ddd;
	padding-left: 5px;
}
.librarians{
	padding: 25px;
	position: static;
	margin-top: 150px;
	margin-bottom: 150px;
	width: 1000px;
	height: 1500px;
	background:rgba(0, 0, 0, 0.65);
	overflow: none;
	border-radius: 10px;
	/*box-shadow: 5px 20px 50px #000;*/
	background-image: none;
	display: flex;
	flex-direction: column;
}
.lst{
	align-self: center;
	padding: 4px;
	position: static;
	margin-top: 20px;
	margin-bottom: 30px;
	width: 100%;
	height: 100%;
	background: white;
	overflow: auto;
	background-image: none;
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
.search{
	width: 350px;
	height: 20px;
	background: white;
	margin: 20px auto;
	padding: 10px;
	border-radius: 5px;
}
.text{
	width: 140px;
	height: 20px;
	background: white;
	margin: 20px auto;
	padding: 10px;
	border-radius: 5px;
}
.address{
	width: 200px;
	height: 20px;
	background: white;
	margin: 20px auto;
	padding: 10px;
	border-radius: 5px;
}
.dropdownbutton {
  background-color: rgb(133, 1, 1);
  color: white;
  padding: 3px;
  font-size: 16px;
  border: none;
}
.dropdown {
	display: inline-block;
	position: relative;
  
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgb(104, 104, 104);
  z-index: 1;
}
.dropdown-content a {
  color: black;
  padding: 5px 10px;
  text-decoration: none;
  display: block;
}
.dropdown-content a:hover {
	background-color: #ddd;
}
.dropdown:hover .dropdown-content {
	display: block;
}
.dropdown:hover .dropdownbutton {
	background-color: rgb(87, 1, 1);
}
.button { 
height: 50px; 
width: 175px; 
} 
</style>