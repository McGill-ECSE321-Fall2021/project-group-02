<template>
  <div class="manageitemspage">
    <body>
      <div class="header">
			<router-link to="/"><h1>Montreal Library</h1></router-link>
			<router-link to="/signup"><a class="btn">Sign Up/Log In</a></router-link>
			<router-link to="/items"><a class="btn">Items Information</a></router-link>
			<router-link to="/"><a class="btn">Home</a></router-link>
		</div>
      <div class="manageItem">
		<h2> Manage Items </h2>
		<form>
			<input class="text" v-model="itemID_b" placeholder="Enter item ID" required="">
			<input class="text" v-model="patronID_b" placeholder="Enter patron ID" required="">
			<button @click="borrowItem(itemID_b, patronID_b)">Borrow Item</button>
			<button @click="returnItem(itemID_b, patronID_b)">Return Item</button>
			<p><span v-if="errorMsg" style="color:red">Error: {{errorMsg}} </span></p>
		</form>
		<form>
			<input class="text" v-model="itemID_ar" placeholder="Enter item ID" required="">
			<input class="text" v-model="librarianID_ar" placeholder="Enter user ID" required="">
			<button @click="archiveItem(itemID_ar, librarianID_ar)">Move to Archived</button>
			<button @click="makeAvailable(itemID_ar, librarianID_ar)">Remove from Archived</button>
		</form>
		<form>
			<input class="text" name="itemID_d" placeholder="Enter item ID" required="">
			<input class="text" name="userID_d" placeholder="Enter user ID" required="">
			<button @click="damageItem(itemID_d, librarianID_d)">Move to Damaged</button>
			<button @click="makeAvailable(itemID_d, librarianID_d)">Remove from Damaged</button>
		</form>
		<form>
			<input type="text" v-model="itemName" placeholder="Enter item name" required="">
			<input type="text" v-model="itemAuthor" placeholder="Enter item author" required="">
			<input type="text" v-model="librarianID" placeholder="Enter your user ID" required="">
			<select type= "text" v-model="itemType">
				<option disabled value="">Select item type</option>
				<option>Book</option>
				<option>Album</option>
				<option>Movie</option>
				<option>Journal</option>
				<option>Newspaper</option>
			</select>
			<button @click="createItem(itemType, itemName, itemAuthor, librarianID)">Add New Item</button>
		</form>
		<form>
			<input type="text" v-model="itemID_del" placeholder="Enter item ID" required="">
			<input type="text" v-model="librarianID_del" placeholder="Enter user ID" required="">
			<button @click="deleteItem(itemID_del, librarianID_del)">Delete Item</button>
		</form>
      </div>
    </body>
  </div>
</template>

<script src="../store/ManageItems.js">
</script>

<style>
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
	
	width: 400px;
	background:rgb(112, 1, 1);
	padding: 10px 10px;
}

h2{
	color:white;
	font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	text-align: center;
	font-weight:350;
	height: 70px;
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

input{
	background: white;
	padding: 10px;
	border: none;
	outline: none;
	border-radius: 5px;
	height: 100%;
}

.manageItem {
	background:rgba(0, 0, 0, 0.65);
	border-radius: 10px;
	background-image: none;
	padding: 10px;
	height: 100%;
	display: flex;
	flex-direction: column;
	gap: 10px;
	align-items: center;
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
	vertical-align: top;
    height: 100%;
}button:hover{
	background: black;
    color:white;
}

</style>