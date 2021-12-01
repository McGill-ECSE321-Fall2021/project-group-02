<template>
	<body>
		<div class="viewschedule">
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
				<div class=schedules>
					<h3>Schedules</h3>
					<form>
						<input class="search" v-model="librarianID" placeholder="Search librarian">
						 <button class="srch" @click="getDailySchedules(librarianID); getWeeklySchedules(librarianID);">Search</button>
						 <div class=list>
							 <table>
								 <tr><th colspan="10">Week Of (Schedules start on Monday and end on Sunday)</th></tr>
								 <tr style="margin:0px; white-space:nowrap; display: table-row;">
									<th scope="col">Start Date</th>
									<th scope="col">End Date</th>
								</tr>
								<tr v-for="weeklySchedule in weeklySchedules" :key="weeklySchedule.id">
									<td>{{ weeklySchedule.startDate }}</td>
									<td>{{ weeklySchedule.endDate }}</td>
								</tr>
								<tr><th colspan="10">Daily</th></tr>
							<tr>
								<th>Day of Week</th>
								<th>Start Time</th>
								<th>End Time</th>
							</tr>
							<tr v-for="dailySchedule in dailySchedules" :key="dailySchedule.id">
								<td>{{ dailySchedule.day  }}</td>
								 <td>{{ dailySchedule.startTime }}</td>
								 <td>{{ dailySchedule.endTime }}</td>
							</tr>
							</table>
							</div>
					</form>
				</div>
			</body>
		</div>
	</body>
</template>

<script src="../store/ViewLibrarianSchedule.js">
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
	height: 130px;
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
h4{
	text-align: center;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
.calendar{
	align-items: center;
	text-align: center;
	background: white;
	display: block;
}
.schedules{
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
.list{
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
  margin: 75px 5px;
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
