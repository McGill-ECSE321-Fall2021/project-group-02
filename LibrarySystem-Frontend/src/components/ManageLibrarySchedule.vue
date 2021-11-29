<template>
  <div id="manageLibrarySchedule">
      <div class="header">
				  <router-link to="/"><h2>Montreal Library</h2></router-link>
        <div class="rightHeader">
				  <router-link to="/signup"><a class="add">Log Out</a></router-link>
        	<router-link to="/items"><a class="add">Items Information</a></router-link>
        	<router-link to="/"><a class="add">Home</a></router-link>
        </div>
		  </div>
    
    <div class="calendar">
      <div class="wrapper" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
            <!-- <b-form-datepicker id="datepicker-placeholder" placeholder="Choose a date" v-model="value" :date-disabled-fn="dateDisabled" :min="min" :max="max" size="lg" block locale="en"></b-form-datepicker> -->
            <b-calendar v-model="value" :date-disabled-fn="dateDisabled" :min="min" :max="max" block locale="en"></b-calendar>
            <div class="text-center" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin-top: 100px; margin-bottom: 100px;">
            <button class="button" v-b-toggle.sidebar-no-header style="background:rgb(112, 1, 1); color: white;padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block;
              font-size: 16px;" size="lg">Change Schedule</button>
            <p style="color: white">Date: <b>'{{ value }}'</b></p>
            </div>
      </div>
    </div>
        <div class="sidebar" >
            <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header shadow bg-variant="light"
            text-align="center">
              <template #footer="{ hide }">
                 <div class="d-flex bg-dark text-light align-items-center px-3 py-2">
                  <strong class="mr-auto"></strong>
                  <b-button style="background-color: #c82333" size="sm" @click="hide">Close</b-button>
                </div>
              </template>
              <template>
                <div class="container">
                  <h3 id="sidebar-no-header-title">Library Schedule</h3>
                  <div id="divider" style="color: white">''</div>
                  <div class="center">
                    <!-- <button style="background-color: #c82333; color: white; font-size: 20px; padding: 10px 24px; border: 2px solid gray"
                      v-bind:disabled="!selectedLibrarian.id || !startTime || !endTime || !value.getDay()" @click="createDailySchedule(value.getDay(),startTime,endTime,id)">Publish and Notify</button> -->
                  </div>
                </div>
                <div class="p-3" text-align="center" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
                    <h5 id="employee" style="text-align: center">Employees</h5>
                    <div class="table_outer">
                    <table class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th class="col-id-no fixed-header">ID</th>
                          <th class="col-first-name fixed-header">First Name</th>
                          <th class="col-last-name fixed-header">Last Name</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="librarian in librarians" :key="librarian.id">
                          <td>{{ librarian.id }}</td>
                          <td>{{ librarian.firstName }}</td>
                          <td>{{ librarian.lastName }}</td>
                        </tr>
                      </tbody>
                    </table>
                    </div>
                </div>
                </template>
                <div class="container0" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
                  <h5 id="sidebar-timeSet-title">Select Employee</h5>
                  <select v-model="selectedLibrarian">
                    <option disabled value="">Select Employee ID</option>
                    <option v-for="librarian in librarians" :key="librarian.id">
                      {{ librarian.id }}
                    </option>
                  </select>
                </div>
                <div id="divider" style="color: white">''</div>
                <div class="container" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
                  <h5 id="sidebar-timeSet-title">Set Time</h5>
                  <input type="time" v-model="startTime" placeholder="HH:mm">
                  <input type="time" v-model="endTime" placeholder="HH:mm">
                </div>
                <div id="divider" style="color: white">''</div>
                <div class="container1">
                  <img src="../assets/logo.png" style="text-align: center">
                </div>
                <div class="container2" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
                  <h3 style="text-align: center">Montreal Library</h3>
                </div>
            </b-sidebar>
        </div>
    </div>
</template>

<script src="../store/ManageLibrarySchedulePage.js">
</script>


<style>
body{
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Jost', sans-serif;
	background-image: url("../assets/library.jpg");
}
.add{
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
.add:hover{
  background:#fff;
}
#sidebar-timeSet-title{
  justify-content: center;
  align-items: center;
  margin: 0;
  padding: 0;
  display: flex;
}
.sidebar{
  justify-content: center;
  align-items: center;
  display: flex;
  text-align: center;
}
#sidebar-no-header-title{
  justify-content: center;
  align-items: center;
  margin: 0;
  padding: 0;
  display: flex;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
mb-3{
  justify-content: center;
  align-items: center;
  display: flex;
  text-align: center;
}
input[type="text"] {
  display: block;
  margin: 0 auto;
}
.container1 img {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.container2 {
  display: block;
  margin-left: auto;
  margin-right: auto;
  text-justify: center;
  text-align: center;
  font-size: 150%;
  color:black;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
  color: white;
}
router-link{
  color: white;
}
.header a{
  color: white;
}
.header a:hover{
  color: black;
}
th {
  position: sticky;
}
input[type="time"] {
  display: block;
  margin: 0 auto;
}
.center {
  margin: 0 auto;
  display: block;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.calendar {
  padding: 25px;
	position: static;
	margin-top: 150px;
	margin-bottom: 150px;
	width: 400px;
	height: 600px;
	background:rgba(0, 0, 0, 0.65);
	overflow: none;
	border-radius: 10px;
	/*box-shadow: 5px 20px 50px #000;*/
	background-image: none;
	display: flex;
	flex-direction: column;
}
.table table-striped table-bordered {
  overflow-y: auto;
}
.table_outer {
  overflow: auto;
  height: 15em;
}
.fixed-header {
  z-index: 50;
}
thead th {
  top: 0;
  position: sticky;
  background-color: white;
}
</style>