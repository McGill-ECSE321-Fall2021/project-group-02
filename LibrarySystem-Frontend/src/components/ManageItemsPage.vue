<template>
  <div class="manageitemspage">
    <h1>Manage Items</h1>
    
    <a class="btn"><router-link to="/">Home</router-link></a>
    <a class="btn"><router-link to="/signup">Sign Up/Log In</router-link></a>
    <a class="btn">Items Information</a>
    <a class="btn">Manage Items</a>

    <body>

      <div class="itemID">
        <form>
          <input type="text" name="itemID" placeholder="Enter Item ID" required="">
          <button>Enter</button>
        </form>
      </div>

      <div class="itemInfo">
        <label for="chk" aria-hidden="true">Item Information</label>
        <p>Item ID, name, author here</p>
        <button>Borrow Item</button>
        <button>Return Item</button>
        <button>Archive Item</button>
        <button>Damage Item</button>
        <button>Add New Item</button>
        <button>Delete Item</button>
      </div>

    </body>
  </div>
</template>


<script>
</script>


<style>
  h1{
    color:white;
    font-weight:100;
    width: 900px;
    margin: 0 auto;
    background:rgba(0, 0, 0, 0.6);
    padding: 10px 20px;

  }

  .btn{
    margin: 20px 0px;
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

  body{
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    justify-content: start;
    gap: 20px;
    align-items: center;
    min-height: 100vh;
    font-family: 'Jost', sans-serif;
    background-image: url("../assets/library.jpg");
  }

  .itemID{
    width: 30%;
    height: 100%;
    padding: 10px;
    background: white;
    outline: none;
    border: none;
    border-radius: 5px;
  }

  input{
    width: 60%;
    height: 20px;
    background: white;
    justify-content: center;
    display: inline;
    margin: 20px auto;
    padding: 10px;
    border: solid;
    border-color: rgb(183, 201, 241);
    outline: none;
    border-radius: 5px;
  }
  input:hover{
    border-color: rgb(135, 169, 241);
  }

  button{
    width: 30%;
    height: 40px;
    justify-content: center;
    display: inline;
    color: black;
    background: white;
      
    font-size: 1em;
    font-weight: bold;
    margin-top: 20px;
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

  label{
    color: rgb(0, 0, 0);
    font-size: 2.3em;
    justify-content: center;
    display: flex;
    margin: auto;
    font-weight: bold;
    transition: .5s ease-in-out;
  }

  .itemInfo{
    width: 60%;
    height: 100%;
    padding: 10px;
    background: white;
    outline: none;
    border: none;
    border-radius: 5px;

    transition: .8s ease-in-out;
  }

  p {
    text-align: center;
  }

</style>

