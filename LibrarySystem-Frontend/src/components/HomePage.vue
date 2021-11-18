<template>
  <div class="welcomepage">
    
    <h1>Welcome to the Library Management System</h1>
      <div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#login">Log In</a>
        <a href="#signup">Sign Up</a>
        <a href="#iteminfo">Library Items</a>
      </div>
  </div>
</template>
<script>
</script>
<style>
.welcomepage {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
  /* Add a black background color to the top navigation */
.topnav {
  background-color: #333;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  float: center;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 21px;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
  background-color: #04AA6D;
  color: white;
}
</style>