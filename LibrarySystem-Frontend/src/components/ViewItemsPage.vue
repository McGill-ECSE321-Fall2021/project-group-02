<template>
	<body>
		<div class="viewitemspage">
			<div class="header">
				<router-link to="/"><h2>Montreal Library</h2></router-link>
				<template v-if="getTypeOfUser().includes('Patron')">
					<router-link to="/userProfile"><a class="btn">User Profile</a></router-link>
					<router-link to="/items"><a class="btn">Items Information</a></router-link>
					<router-link to="/homeAfterLogin"><a class="btn">Home</a></router-link>
				</template>
				<template v-else-if="getTypeOfUser().includes('Librarian')">
					<router-link to="/librarians"><a class="btn">Manage Employment</a></router-link>
					<router-link to="/manageLibrarySchedule"><a class="btn">Manage Library Schedule</a></router-link>
					<router-link to="/schedules"><a class="btn">View Librarian Schedule</a></router-link>
					<router-link to="/manageitems"><a class="btn">Manage Items</a></router-link>
					<router-link to="/createPatron"><a class="btn">Create Patron</a></router-link>
					<router-link to="/userProfileLibrarian"><a class="btn">User Profile</a></router-link>
					<router-link to="/items"><a class="btn">Items Information</a></router-link>
					<router-link to="/homePageLibrarian"><a class="btn">Home</a></router-link>
				</template>
				<template v-else-if="getTypeOfUser().includes('HeadLibrarian')">
					<router-link to="/librarians"><a class="btn">Manage Employment</a></router-link>
					<router-link to="/manageLibrarySchedule"><a class="btn">Manage Library Schedule</a></router-link>
					<router-link to="/schedules"><a class="btn">View Librarian Schedule</a></router-link>
					<router-link to="/manageitems"><a class="btn">Manage Items</a></router-link>
					<router-link to="/createPatron"><a class="btn">Create Patron</a></router-link>
					<router-link to="/userProfileLibrarian"><a class="btn">User Profile</a></router-link>
					<router-link to="/items"><a class="btn">Items Information</a></router-link>
					<router-link to="/homePageHeadLibrarian"><a class="btn">Home</a></router-link>
				</template>
				<template v-else>
        			<router-link to="/signup"><a class="btn">Sign Up/Log In</a></router-link>
					<router-link to="/items"><a class="btn">Items Information</a></router-link>
					<router-link to="/"><a class="btn">Home</a></router-link>
				</template>
				
				
				
			</div>
			<body>
				<div class=items>
					<h3>List of Contents</h3>
					
					<form>
						<input type="search" v-model="searchTerm" placeholder="Search library contents">
						<select type= "text" v-model="searchType">
							<option disabled value="">Select search category</option>
							<option>None</option>
							<option>Creator</option>
							<option>Name</option>
						</select>
						<button @click ="searchItems(searchTerm, searchType)">Search</button>
						
						<div class="dropdown">
							<button class="dropdownbutton">Category ▼</button>
							<div class="dropdown-content">
								<a href="#/items">All</a>
								<a href="#/items/albums">Albums</a>
								<a href="#/items/books">Books</a>
								<a href="#/items/movies">Movies</a>
								<a href="#/items/newspapers">Newspapers</a>
								<a href="#/items/journals">Journals</a>
							</div>
						</div>
					</form>
					
					<div class=lst>
						<table>
							<template v-if="($route.name).includes('books') || ($route.name).includes('items')">
								<tr></tr>
								<tr><th colspan="10">Books</th></tr>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Title</th>
									<th scope="col">Author</th>
									<th scope="col">Availability</th>
								</tr>
								<tr v-for="book in books" :key="book.id">
									<td>{{ book.id }}</td>
									<td>{{ book.title }}</td>
									<td>{{ book.author }}</td>
									<td v-if="book.isAvailable"> ✓ </td>
									<td v-else> ✕ </td>
								</tr>
							</template>
							<template v-if="($route.name).includes('albums') || ($route.name).includes('items')">
								<tr></tr>
								<tr><th colspan="10">Albums</th></tr>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Title</th>
									<th scope="col">Artist</th>
									<th scope="col">Availability</th>
								</tr>
								<tr v-for="album in albums" :key="album.id">
									<td>{{ album.id }}</td>
									<td>{{ album.title }}</td>
									<td>{{ album.artist }}</td>
									<td v-if="album.isAvailable"> ✓ </td>
									<td v-else> ✕ </td>
								</tr>
							</template>
							<template v-if="($route.name).includes('movies') || ($route.name).includes('items')">
								<tr></tr>
								<tr><th colspan="10">Movies</th></tr>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Title</th>
									<th scope="col">Director</th>
									<th scope="col">Availability</th>
								</tr>
								<tr v-for="movie in movies" :key="movie.id">
									<td>{{ movie.id }}</td>
									<td>{{ movie.title }}</td>
									<td>{{ movie.director }}</td>
									<td v-if="movie.isAvailable"> ✓ </td>
									<td v-else> ✕ </td>
								</tr>
							</template>
						</table>
						<table>
							<template v-if="($route.name).includes('newspapers') || ($route.name).includes('items')">
								<tr></tr>
								<tr><th colspan="10">Newspapers</th></tr>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Name</th>
									<th scope="col">Date</th>
								</tr>
								<tr v-for="newspaper in newspapers" :key="newspaper.id">
									<td>{{ newspaper.id }}</td>
									<td>{{ newspaper.name }}</td>
									<td>{{ newspaper.date }}</td>
								</tr>
							</template>
							<template v-if="($route.name).includes('journals') || ($route.name).includes('items')">
								<tr></tr>
								<tr><th colspan="10">Journals</th></tr>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Name</th>
									<th scope="col">Date</th>
								</tr>
								<tr v-for="journal in journals" :key="journal.id">
									<td>{{ journal.id }}</td>
									<td>{{ journal.name }}</td>
									<td>{{ journal.date }}</td>
								</tr>
							</template>
						</table>
					</div>
				</div>
			</body>
		</div>
	</body>
</template>

<script src="../store/ViewItemsPage.js">
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
	font-weight: 350;
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
button {
  background-color: rgb(133, 1, 1);
  color: white;
  padding: 3px;
  font-size: 16px;
  border: none;
}
button:active{
	background-color: rgb(87, 1, 1);
}
select{
	background-color: white;
	color: black;
	height: 29px;
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
td{
	height: 10px;
	overflow: hidden;
}
th{
	font-weight: 500;
}
th:nth-child(1) {
	width: 10%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(2) {
	width: 45%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(3) {
	width: 35%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(4) {
	width: 15%;
	background: #ddd;
	padding-left: 5px;
}
td:nth-child(4) {
	text-align: center;
}

.items{
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

input{
	width: 350px;
	height: 30px;
	background: white;
	margin: 20px auto;
	padding: 10px;
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

.header a{
	color: white;
}
.header a:hover{
	color: black;
}
.btn{
  float:right;
  margin: 45px 5px;
  color:white;
  background:rgba(0, 0, 0, 0.6);
  padding:10px 20px;
  font-size:12px;
  text-decoration:none;
  letter-spacing:2px;
  text-transform:uppercase;
}
.btn:hover{
  background: white;
  color:white;
}

</style>