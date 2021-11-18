<template>
  <div class="welcomepage">
    <h1>Welcome To The Montreal Library Website!</h1>
    
    <a href="#" class="btn">Home</a>
    <a href="#" class="btn">Sign Up</a>
    <a href="#" class="btn">Log In</a>
    <a href="#" class="btn">Items Information</a>
    
</div>
</template>


<script>
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
  background: #ADD8E6;
  padding:75px;
  text-align:center;
  font-family: "Courier", sans-serif;
}

h1{
  color:white;
  font-weight:100;
}

.btn{
  color:white;
  background:rgba(0, 0, 0, 0.5);
  padding:10px 20px;
  font-size:12px;
  text-decoration:none;
  letter-spacing:2px;
  text-transform:uppercase;
}

.btn:hover{
  background:#fff;
}

.footer{
  font-size:8px;
  color:#fff;
  clear:both;
  display:block;
  letter-spacing:5px;
  border:1px solid #fff;
  padding:5px;
  text-decoration:none;
  width:210px;
  margin:auto;
  margin-top:400px;
}
</style>