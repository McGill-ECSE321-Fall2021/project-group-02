<template>
  <div class="manageitemspage">
    <body>
      <div class="header">
              <h1><router-link to="/">Montreal Library</router-link></h1>
            <a class="btn"><router-link to="/signup">Sign Up/Log In</router-link></a>
            <a class="btn"><router-link to="/items">Items Information</router-link></a>
            <a class="btn"><router-link to="/">Home</router-link></a>
		</div>
      <div class="manageItem">
		<h2> Manage Items </h2>
        <div id="borrowItem">
          <button type="button" class="button" @click="showModal">Borrow Item</button>
		  <Modal v-show="isModalVisible" @close="closeModal">
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
      </div>
    </body>
  </div>
</template>


<script>
  import Modal from './Modal.vue';

  export default {
    name: 'ManageItemsPage',
    components: {
      Modal,
    },
    data() {
      return {
        isModalVisible: false,
      };
    },
    methods: {
      showModal() {
        this.isModalVisible = true;
      },
      closeModal() {
        this.isModalVisible = false;
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
}

.manageItem {
	background:rgba(0, 0, 0, 0.65);
	border-radius: 10px;
	background-image: none;
	padding: 5px;
	height: 150px;
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
}
button:hover{
	background: black;
    color:white;
}

</style>

