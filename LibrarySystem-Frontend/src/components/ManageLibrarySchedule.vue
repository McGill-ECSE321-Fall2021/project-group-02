<template>
  <div id="manageLibrarySchedule">
    <body>
      <body>
      <div class="header">
				  <router-link to="/"><h2 style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">Montreal Library</h2></router-link>
				  <router-link to="/signup"><a class="btn" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">Sign Up/Log In</a></router-link>
        	<router-link to="/items"><a class="btn" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">Items Information</a></router-link>
        	<router-link to="/"><a class="btn" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">Home</a></router-link>
		  </div>
    
    <div class="calendar">
      <div class="wrapper" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
            <label for="datepicker-placeholder-start" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: white">Choose a start date</label>
            <b-form-datepicker id="datepicker-placeholder-start" placeholder="Choose start date" v-model="value" :date-disabled-fn="dateDisabled" :min="min" :max="max" size="lg" block locale="en"></b-form-datepicker>
            <div id="divider" style="color: black">''</div>
            <label for="datepicker-placeholder-end" style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: white">Choose an end date</label>
            <b-form-datepicker id="datepicker-placeholder-end" placeholder="Choose end date" v-model="value1" :date-disabled-fn="dateDisabled" :min="min" :max="max" size="lg" block locale="en"></b-form-datepicker>
            <!-- <b-calendar v-model="value" :date-disabled-fn="dateDisabled" :min="min" :max="max" block locale="en"></b-calendar> -->
            <div id="divider" style="color: black">''</div>

            <table class="table">
              <tr>
                <td>
              <input class="text" v-model="librarianID" placeholder="Librarian ID">
                </td>
              </tr>
              <tr>
                <td>
              
              <input class="text" v-model="id" placeholder="Validation Token">
                </td>
              </tr>

              <tr>
                <td>
                    <button style="background:rgb(112, 1, 1); color: white; text-align: center;" @click="createWeeklySchedule(id, value, value1, librarianID)">Set Week</button>
                </td>
              </tr>
            </table>
            <button class="button" v-b-toggle.sidebar-no-header style="background:rgb(112, 1, 1); color: white;padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block;
              font-size: 16px;" size="lg">Change Schedule</button>
            
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
<button style="background-color: #c82333; color: white; font-size: 20px; padding: 10px 24px; border: 2px solid gray;"
                     @click="createDailySchedule(WeekDay, startTime, endTime, librarianID, id)">Publish and Notify</button>

                  <div id="divider" style="color: white">''</div>
                  
                  <input class="text" v-model="id" placeholder="Validation Token">

                  <div id="divider" style="color: white">''</div>
                  
                    <h5 id="employee" style="text-align: center">Employees</h5>
                    <div id="table-wrapper">
                    <table class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>First Name</th>
                          <th>Last Name</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="librarian in librarians" :key="librarian.id">
                          <td>{{librarian.id}}</td>
                          <td>{{librarian.firstName}}</td>
                          <td>{{librarian.lastName}}</td>
                        </tr>
                      </tbody>
                    </table>
                    </div>
                    <h5 id="sidebar-timeSet-title">Select Employee</h5>
                  <input class="text" v-model="librarianID" placeholder="Librarian ID">
                  <h5 id="sidebar-timeSet-title">Set Time</h5>
                  <input type="text" v-model="startTime" placeholder="Start Time (HH:mm:ss)">
                  <input type="text" v-model="endTime" placeholder="End Time (HH:mm:ss)">

          <b-calendar v-model="value3" :date-disabled-fn="dateDisabled" :min="min" :max="max" block locale="en"></b-calendar>

          <input class="text" v-model="WeekDay" placeholder="WeekDay">
          

                
                </div>
                </template>
            </b-sidebar>
        </div>
        </body>
        </body>
    </div>
</template>

<script src="../store/ManageLibrarySchedulePage.js">
</script>


<style>
#calendar {
  width: 500px;
}
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
/* sidebar-button{
  justify-content: center;
  align-items: center;
  display: block;
  margin: 0 auto;
  text-align: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
} */
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
.table{
  text-align: center;
  border: 0;
}
#table-wrapper{
 	height: 150px;
	width: 300px;
	padding: 0px;
	margin: 0px auto 0px auto;
	overflow: auto;

}
</style>