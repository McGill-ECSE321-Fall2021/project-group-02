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
        <div id="borrowItem">
          <button type="button" class="button" @click="showBorrowModal">Borrow Item</button>
		  <Modal v-show="isBorrowModalVisible" @close="closeModal">
		    <template v-slot:header>
				Borrow Item
			</template>

			<template v-slot:body>
				<form>
					<input type="text" name="itemID" placeholder="Enter item ID" required="">
					<input type="text" name="patronID" placeholder="Enter patron ID" required="">
					<button >Enter</button>
				</form>
			</template>

			<template v-slot:footer>
			</template>
		  </Modal>
        </div>  

		<div id="returnItem">
          <button type="button" class="button" @click="showReturnModal">Return Item</button>
		  <Modal v-show="isReturnModalVisible" @close="closeModal">
		    <template v-slot:header>
				 Return Item
			</template>
			<template v-slot:body>
				<form>
					<input type="text" v-model="itemID" placeholder="Enter item ID" required="">
					<input type="text" v-model="patronID" placeholder="Enter patron ID" required="">
					<button v-bind:disabled="!itemID || !patronID" @click="returnItem(itemID, patronID)">Enter</button>
					<p><span v-if="errorMsg" style="color:red">Error: {{errorMsg}} </span></p>
				</form>
			</template>
			<template v-slot:footer>
			</template>
		  </Modal>
        </div>  

		<div id="archiveItem">
          <button type="button" class="button" @click="showArchiveModal">Archive Item</button>
		  <Modal v-show="isArchiveModalVisible" @close="closeModal">
		    <template v-slot:header>
				 Archive Item
			</template>
			<template v-slot:body>
				<form>
					<input type="text" v-model="itemID" placeholder="Enter item ID" required="">
					<input type="text" v-model="librarianID" placeholder="Enter user ID" required="">
					<button v-bind:disabled="!itemID || !librarianID" @click="archiveItem(itemID, librarianID)">Move to Archived</button>
					<button >Remove from Archived</button> <!-- missing method -->
				</form>
			</template>
			<template v-slot:footer>
			</template>
		  </Modal>
        </div>  

		<div id="damageItem">
          <button type="button" class="button" @click="showDamageModal">Damage Item</button>
		  <Modal v-show="isDamageModalVisible" @close="closeModal">
		    <template v-slot:header>
				 Damage Item
			</template>
			<template v-slot:body>
				<form>
					<input type="text" name="itemID" placeholder="Enter item ID" required="">
					<input type="text" name="userID" placeholder="Enter user ID" required="">
					<button >Move to Damaged</button>
					<button >Remove from Damaged</button>
				</form>
			</template>
			<template v-slot:footer>
			</template>
		  </Modal>
        </div>  

		<div id="addItem">
          <button type="button" class="button" @click="showAddModal">Add a New Item</button>
		  <Modal v-show="isAddModalVisible" @close="closeModal">
		    <template v-slot:header>
				 Add a New Item
			</template>
			<template v-slot:body>
				<form>
					<input type="text" v-model="itemID" placeholder="Enter item ID" required="">
					<input type="text" v-model="itemType" placeholder="Enter type of item" required="">
					<input type="text" v-model="itemName" placeholder="Enter item name" required="">
					<input type="text" v-model="itemAuthor" placeholder="Enter item author" required="">
					<input type="text" v-model="librarianID" placeholder="Enter your user ID" required="">
					<button>Enter</button>
				</form>
			</template>
			<template v-slot:footer>
			</template>
		  </Modal>
        </div>  

		<div id="deleteItem">
          <button type="button" class="button" @click="showDeleteModal">Delete Item</button>
		  <Modal v-show="isDeleteModalVisible" @close="closeModal">
		    <template v-slot:header>
				 Delete Item
			</template>
			<template v-slot:body>
				<form>
					<input type="text" v-model="itemID" placeholder="Enter item ID" required="">
					<input type="text" v-model="librarianID" placeholder="Enter user ID" required="">
					<button v-bind:disabled="!itemID || !librarianID" @click="deleteItem(itemID, librarianID)">Enter</button>
				</form>
			</template>
			<template v-slot:footer>
			</template>
		  </Modal>
        </div>  

      </div>
    </body>
  </div>
</template>

<script src="../store/ManageItems.js">
</script>

<script>
  import Modal from './Modal.vue';

  export default {
    name: 'ManageItemsPage',
    components: {
      Modal,
    },
    data() {
      return {
        isBorrowModalVisible: false,
        isReturnModalVisible: false,
		isArchiveModalVisible: false,
		isDamageModalVisible: false,
		isAddModalVisible: false,
		isDeleteModalVisible: false,
      };
    },
    methods: {
      showBorrowModal() {
        this.isBorrowModalVisible = true;
        this.isReturnModalVisible = false;
		this.isArchiveModalVisible = false,
		this.isDamageModalVisible = false;
		this.isAddModalVisible = false;
		this.isDeleteModalVisible = false;
      },
	  showReturnModal() {
        this.isBorrowModalVisible = false;
        this.isReturnModalVisible = true;
		this.isArchiveModalVisible = false,
		this.isDamageModalVisible = false;
		this.isAddModalVisible = false;
		this.isDeleteModalVisible = false;
      },
	  showArchiveModal() {
		this.isBorrowModalVisible = false;
        this.isReturnModalVisible = false;
		this.isArchiveModalVisible = true,
		this.isDamageModalVisible = false;
		this.isAddModalVisible = false;
		this.isDeleteModalVisible = false;
	  },
	  showDamageModal() {
		this.isBorrowModalVisible = false;
        this.isReturnModalVisible = false;
		this.isArchiveModalVisible = false,
		this.isDamageModalVisible = true;
		this.isAddModalVisible = false;
		this.isDeleteModalVisible = false;
	  },
	  showAddModal() {
		this.isBorrowModalVisible = false;
        this.isReturnModalVisible = false;
		this.isArchiveModalVisible = false,
		this.isDamageModalVisible = false;
		this.isAddModalVisible = true;
		this.isDeleteModalVisible = false;
	  },
	  showDeleteModal() {
		this.isBorrowModalVisible = false;
        this.isReturnModalVisible = false;
		this.isArchiveModalVisible = false,
		this.isDamageModalVisible = false;
		this.isAddModalVisible = false;
		this.isDeleteModalVisible = true;
	  },
      closeModal() {
        this.isBorrowModalVisible = false;
        this.isReturnModalVisible = false;
		this.isArchiveModalVisible = false,
		this.isDamageModalVisible = false;
		this.isAddModalVisible = false;
		this.isDeleteModalVisible = false;
      }
    }
  };
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
}
button:hover{
	background: black;
    color:white;
}

</style>