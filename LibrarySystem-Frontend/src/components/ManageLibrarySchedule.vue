<template>
  <div>
    <body>
      <div class="header">
				  <h2><router-link to="/">Montreal Library</router-link></h2>
		  </div>
    <div id="calendar">
      <div class="main-calendar">
        <b-row>
          <b-col md="auto">
            <b-form-datepicker id="datepicker-placeholder" placeholder="Choose a date" v-model="value" :date-disabled-fn="dateDisabled" :min="min" :max="max" locale="en"></b-form-datepicker>
            <b-calendar v-model="value" :date-disabled-fn="dateDisabled" :min="min" :max="max" locale="en"></b-calendar>
          </b-col>
        </b-row>
        </div>
    </div>
        <div class="sidebar" >
            <b-button v-b-toggle.sidebar-no-header :pressed.sync="myToggle" style="border-radius: 25px;" variant="danger">Change Schedule</b-button>
            <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header shadow bg-variant="light"
            text-align="center">
              <template #footer="{ hide }">
                 <div class="d-flex bg-dark text-light align-items-center px-3 py-2">
                  <strong class="mr-auto"></strong>
                  <b-button variant="danger" size="sm" @click="hide">Close</b-button>
                </div>
              </template>
              <template>
                <div class="container">
                  <h4 id="sidebar-no-header-title">Library Schedule</h4>
                  <b-button id="sidebar-button" pill block variant="danger" text-align="center">Publish and Notify</b-button>
                </div>
                <div class="p-3" text-align="center">
                    <h5 id="employee" style="text-align: center">Employees</h5>

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
                      
                    <nav class="mb-3">
                        <b-nav vertical>
                            <label style="text-align: center">Employee 1</label>
                            <label style="text-align: center">Employee 2</label>
                            <label style="text-align: center">Employee 3</label>
                            <label style="text-align: center">Employee 4</label>
                        </b-nav>
                    </nav>
                </div>
                </template>
                <div class="container0">
                  <h5 id="sidebar-timeSet-title">Select Employee ID</h5>
                  <input type="text" name="Employee ID" style="border-radius: 25px;" placeholder="Enter Employee ID">
                </div>
                <div id="divider" style="color: white">''</div>
                <div class="container">
                  <h5 id="sidebar-timeSet-title">Set Time</h5>
                  <input type="text" name="startTime" style="border-radius: 25px;" placeholder="Start Time">
                  <input type="text" name="endTime" style="border-radius: 25px;" placeholder="End Time">
                </div>
                <div id="divider" style="color: white">''</div>
                <div class="container1">
                  <img src="../assets/logo.png" style="text-align: center">
                </div>
                <div class="container2">
                  <h3 style="text-align: center">Montreal Library</h3>
                </div>
            </b-sidebar>
        </div>
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
sidebar{
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
#sidebar-button{
  justify-content: center;
  align-items: center;
  display: flex;
  text-align: center;
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
</style>