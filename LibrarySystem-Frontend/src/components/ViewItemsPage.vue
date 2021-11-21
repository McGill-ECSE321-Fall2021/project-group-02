<template>
	<body>
		<div class="viewitemspage">
			<div class="header">
				<h2>Montreal Library</h2>
			</div>
			<body>
				<div class=items>
					<h3>List of Contents</h3>
					
					<form>
						<input type="search" name="srch" placeholder="Search library contents">
						<div class="dropdown">
						<button class="dropdownbutton">Category</button>
							<div class="dropdown-content">
								<a href="#">Albums</a>
								<a href="#">Books</a>
								<a href="#">Movies</a>
								<a href="#">Newspapers</a>
								<a href="#">Journals</a>
							</div>
						</div>
					</form>
					
					<div class=lst>
						<table>
							<tr>
								<th>Name</th>
								<th>Author</th>
								<th>Availability</th>
								<th>ID</th>
							</tr>
						</table>
					</div>
				</div>
			</body>
		</div>
	</body>
</template>
<script>

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
	width: 55%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(2) {
	width: 20%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(3) {
	width: 15%;
	background: #ddd;
	padding-left: 5px;
}
th:nth-child(4) {
	width: 10%;
	background: #ddd;
	padding-left: 5px;
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

</style>