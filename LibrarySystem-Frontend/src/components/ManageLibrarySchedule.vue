<template>
  <div>
    <body>
    <div id="calendar">
      <div class="main-calendar">
        <b-row>
          <b-col md="auto">
            <b-calendar locale="en-US"></b-calendar>
          </b-col>
        </b-row>
        </div>
    </div>
        <div class="sidebar">
            <b-button v-b-toggle.sidebar-no-header>Change Schedule</b-button>
            <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header shadow>
                <div class="p-3">
                    <h4 id="sidebar-no-header-title">Library Schedule</h4>
                    <b-button id="sidebar-button" variant="primary">Publish and Notify</b-button>
                    <h5 id="employee">Employees</h5>
                    <nav class="mb-3">
                        <b-nav vertical>
                            <label>Employee 1</label>
                            <label>Employee 2</label>
                            <label>Employee 3</label>
                            <label>Employee 4</label>
                        </b-nav>
                    </nav>
                </div>
                <div class="timeSet">
                  <h4 id="sidebar-timeSet-title">Set Time</h4>
                  <input type="text" name="startTime" placeholder="Start Time">
                  <input type="text" name="endTime" placeholder="End Time">
                </div>
            </b-sidebar>
        </div>
        </body>
    </div>
</template>

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
}
#sidebar-no-header-title{
  justify-content: center;
  align-items: center;
  margin: 0;
  padding: 0;
  display: flex;
}
#sidebar-button{
  justify-content: center;
  align-items: center;
  display: flex;
  text-align: center;
}
</style>