<template>
    <div class="createpatronpage">
        <head>
            <title>Slide Navbar</title>
            <link rel="stylesheet" type="text/css" href="slide navbar style.css">
            <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
        </head>
        <body>
            <div class="main">
                <input type="checkbox" id="chk" aria-hidden="true">
                <div class="createpatron">
                    <form>
                        <label for="chk" aria-hidden="true">Create Patron</label>
                        <input type="text" name="firstName" placeholder="First Name" required="">
                        <input type="text" name="lastName" placeholder="Last Name" required="">
                        <input type="text" name="username" placeholder="Username" required="">
                        <input type="text" name="city" placeholder="City" required="">
                        <input type="text" name="address" placeholder="Address" required="">
                        <input type="email" name="email" placeholder="Email" required="">
                        <input type="password" name="pswd" placeholder="Password" required="">
                        <button>Create Patron</button>
                    </form>
                </div>
           </div>
        </body>
    </div>
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
        min-height: 100vh;
        font-family: 'Jost', sans-serif;
    }
    .main{
        width: 350px;
	    height: 600px;
	    background: red;
	    overflow: hidden;
	    background: url("https://doc-08-2c-docs.googleusercontent.com/docs/securesc/68c90smiglihng9534mvqmq1946dmis5/fo0picsp1nhiucmc0l25s29respgpr4j/1631524275000/03522360960922298374/03522360960922298374/1Sx0jhdpEpnNIydS4rnN4kHSJtU1EyWka?e=view&authuser=0&nonce=gcrocepgbb17m&user=03522360960922298374&hash=tfhgbs86ka6divo3llbvp93mg4csvb38") no-repeat center/ cover;
	    border-radius: 10px;
	    box-shadow: 5px 20px 50px #000;
    }
    #chk{
        display: none;
    }
    .createpatron{
        position: relative;
        width: 100%;
        height: 100%;
    }
    label{
        color: #fff;
        font-size:2.3em;
        justify-content: center;
        display: flex;
        margin: 60px;
        font-weight: bold;
        cursor: pointer;
        transition: .5s ease-in-out;
    }
    input{
        width: 60%;
        height: 20px;
        background: white;
        justify-content: center;
        display:flex;
        margin: 20px auto;
        padding: 10px;
        border: none;
        outline: none;
        border-radius: 5px;
    }
    button{
        width: 60%;
        height: 40px;
        margin: 10px auto;
	    justify-content: center;
	    display: block;
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
</style>