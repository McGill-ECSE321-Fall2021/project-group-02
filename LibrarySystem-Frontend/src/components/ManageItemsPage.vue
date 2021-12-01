<template>
  <div class="manageitemspage">
    <body>
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
	  <div class = "containerHeader">
		<h2> Manage Items </h2>
	  </div>
      <div class="manageItems">
		<input type="checkbox" id="chk_b" aria-hidden="true">
		<input type="checkbox" id="chk_r" aria-hidden="true">
		<input type="checkbox" id="chk_a" aria-hidden="true">
		<input type="checkbox" id="chk_da" aria-hidden="true">
		<input type="checkbox" id="chk_c" aria-hidden="true">
		<input type="checkbox" id="chk_de" aria-hidden="true">
		<p><span v-if="errorMsg" style="color:red">Error: {{errorMsg}} </span></p>
		<div class=borrowItem>
			<form> <!-- BORROW ITEM -->
				<label for="chk_b" aria-hidden="true">Borrow Item</label>
				<input class="text" v-model="itemID_b" placeholder="Enter item ID" required="">
				<input class="text" v-model="itemName_b" placeholder="Enter item name">
				<input class="text" v-model="patronID_b" placeholder="Enter patron ID" required="">
				<button @click="borrowItem(itemID_b, itemName_b, patronID_b)">Borrow Item</button>
			</form>
		</div>
		<div class=returnItem>
			<form> <!-- RETURN ITEM -->
				<label for="chk_r" aria-hidden="true">Return Item</label>
				<input class="text" v-model="itemID_r" placeholder="Enter item ID" required="">
				<input class="text" v-model="patronID_r" placeholder="Enter patron ID" required="">
				<button @click="returnItem(itemID_b, patronID_b)">Return Item</button>
			</form>
		</div>
		<div class=archiveItem>
			<form> <!-- ARCHIVE ITEM -->
				<label for="chk_a" aria-hidden="true">Archive Item</label>
				<input class="text" v-model="itemID_ar" placeholder="Enter item ID" required="">
				<button @click="archiveItem(itemID_ar)">Move to Archived</button>
				<button @click="makeAvailable(itemID_ar)">Remove from Archived</button>
			</form>
		</div>
		<div class=damageItem>
			<form> <!-- DAMAGE ITEM -->
				<label for="chk_da" aria-hidden="true">Damage Item</label>
				<input class="text" name="itemID_d" placeholder="Enter item ID" required="">
				<button @click="damageItem(itemID_d)">Move to Damaged</button>
				<button @click="makeAvailable(itemID_d)">Remove from Damaged</button>
			</form>
		</div>
		<div class=createItem>
			<form> <!-- CREATE NEW ITEM -->
				<label for="chk_c" aria-hidden="true">Add New Item</label>
				<select type= "text" v-model="itemType">
					<option disabled value="">Select item type</option>
					<option>Book</option>
					<option>Album</option>
					<option>Movie</option>
					<option>Journal</option>
					<option>Newspaper</option>
				</select>
				<input type="text" v-model="itemName" placeholder="Enter item name">
				<input v-if="itemType == 'Book' || itemType == 'Album' || itemType == 'Movie'" type="text" v-model="itemAuthor" placeholder="Enter item author">
				<input v-if="itemType == 'Journal' || itemType == 'Newspaper'" type="text" v-model="itemDate" placeholder="Enter Date: yyyy-mm-dd">
				<button @click="createItem(itemType, itemName, itemAuthor, itemDate)">Add New Item</button>
			</form>
		</div>
		<div class=deleteItem>
			<form> <!-- DELETE ITEM -->
				<label for="chk_de" aria-hidden="true">Delete Item</label>
				<input type="text" v-model="itemID_del" placeholder="Enter item ID" required="">
				<button @click="deleteItem(itemID_del)">Delete Item</button>
			</form>
		</div>
      </div>
    </body>
  </div>
</template>

<script src="../store/ManageItems.js">
</script>

<style>
/* Navigation Bar */
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
.header h2{
	text-align: left;
	color:white;
	font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	font-weight:350;
	position: fixed;
	top: 0;
	left: 0;
	font-size: 30px;
	height: 50px;
	
	width: 250px;
	background:rgb(112, 1, 1);
	padding: 10px 10px;
}
h1{
	text-align: left;
	color:white;
	font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	font-weight:350;
	position: fixed;
	top: 0;
	left: 0;
	width: 50%;
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
/* Background */
body{
	margin: auto;
	padding: 0;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-image: url("../assets/library.jpg");
}
/* Manage Items */
.manageItems{
	background:rgba(0, 0, 0, 0.65);	
	border-radius: 10px 10px;
	padding: 25px;
	display: flex;
	flex-direction: row; 
	gap: 10px;
	height: 400px;
	width: 90%;
	text-align: center;
	align-items: baseline;
	overflow: hidden;
}
.containerHeader{
	background:rgba(0, 0, 0, 0.65);	
	border-radius: 10px;
	width: 90%;
	height: 130px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
h2{
	color:white;
	background-color: rgb(112,1,1);
	text-align: center;
	padding: 10px 10px;
	height: 90px;
	width: 400px;

	font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	font-size: 45px;
	font-weight:350;
}
.borrowItem {
	background: white;
	height: 100%;
	border-radius: 60% / 10%;
	padding: 10px;
	transform: translateY(260px);
	transition: .8s ease-in-out;
}
.returnItem{
	background: white;
	height: 100%;
	border-radius: 60% / 10%;
	padding: 10px;
	transform: translateY(260px);
	transition: .8s ease-in-out;
}
.archiveItem{
	background: white;
	height: 100%;
	border-radius: 60% / 10%;
	padding: 10px;
	transform: translateY(260px);
	transition: .8s ease-in-out;
}
.damageItem{
	background: white;
	height: 100%;
	border-radius: 60% / 10%;
	padding: 10px;
	transform: translateY(260px);
	transition: .8s ease-in-out;
}
.createItem{
	background: white;
	height: 100%;
	border-radius: 60% / 10%;
	padding: 10px;
	transform: translateY(260px);
	transition: .8s ease-in-out;
}
.deleteItem{
	background: white;
	height: 100%;
	border-radius: 60% / 10%;
	padding: 10px;
	transform: translateY(260px);
	transition: .8s ease-in-out;
}
label{
	color: black;
	font-size: 2.3em;
	font-weight: bold;
	transform: scale(.6);
	cursor: pointer;
	transition: .5s ease-in-out;
}
form{
	display: flex;
	flex-direction: column;
	gap: 3px;
}
input{
	border-radius: 5px;
}
select{
	border-color: black;
}
button{
	color: white;
	background: black;
	border: none;
	border-radius: 5px;
	transition: .2s ease-in;
}
button:hover{
	background: rgb(133,1,1);
    color:white;
}

/* Pop-up */
#chk_b {
	display: none;
}
#chk_r {
	display: none;
}
#chk_a {
	display: none;
}
#chk_da {
	display: none;
}
#chk_c {
	display: none;
}
#chk_de {
	display: none;
}
#chk_b:checked ~ .borrowItem{
	transform: translateY(-10px);
}
#chk_r:checked ~ .returnItem{
	transform: translateY(-10px);
}
#chk_a:checked ~ .archiveItem{
	transform: translateY(-10px);
}
#chk_da:checked ~ .damageItem{
	transform: translateY(-10px);
}
#chk_c:checked ~ .createItem{
	transform: translateY(-10px);
}
#chk_de:checked ~ .deleteItem{
	transform: translateY(-10px);
}

</style>
