<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   


    <!DOCTYPE html>
    <html lang="en">
    
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="http://localhost:8000/static/img/logo.png" type="image/x-icon" >
        
         <link rel="stylesheet" type="text/css" href="http://localhost:8000/static/css/material icons.css"> 
          <link rel="stylesheet" type="text/css" href="http://localhost:80000/static/css/materialize.min.css"> 
           <link rel="stylesheet" type="text/css" href="http://localhost:8000/static/css/style.css"> 
        
         <!--<style><%@include file="/WEB-INF/css/material icons.css"%></style>
        <style><%@include file="/WEB-INF/css/materialize.min.css"%></style>
        <style><%@include file="/WEB-INF/css/style.css"%></style> -->
        
      
        <title>Ensias Timetables | Login</title>
    </head>
    
    <body>
    <style>
            body {
                display: flex;
                min-height: 100vh;
                flex-direction: column;
            }
            
            nav {
                display: none;
            }
            
            main {
                flex: 1 0 auto;
            }
            
            body {
                background: #fff;
            }
            
            .input-field input[type=text]:focus+label,
            .input-field input[type=password]:focus+label {
                color: #f2a4a8 !important;
            }
            .input-field input[type=text]:focus,
            .input-field input[type=password]:focus {
                border-bottom: 2px solid #f2a4a8 !important;
                box-shadow: none !important;
            }
            
            .input-field .prefix.active {
                color: #f2a4a8;
            }
    </style>
    <div class="section"></div>
    
   
    <main>
        <center>
            <div style="display: flex;flex-direction: row-reverse;">
                <a href="#" style="padding-right: 3rem;">
                    <div class="btn waves-effect red lighten-2">Go back</div>
                </a>
            </div>
            <div class="section"><img class="responsive-img" src="http://localhost:8000/static/img/logo.png" style="height: 100px;" /></div>
            <div class="container">
                <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
    
                    <form class="col s12" action="/Timetable_App/login" method="post">
                        <div class='row'>
                            <div class='col s12'>
                            </div>
                        </div>
    
                        <div class='row'>
                            <div class='input-field col s12'>
                                <i class="material-icons prefix pt-2">person</i>
                                <input class='validate' type='text' name='username' id='email' />
                                <label for='username'>Enter your username</label>
                            </div>
                        </div>
    
                        <div class='row'>
                            <div class='input-field col s12'>
                                <i class="material-icons prefix pt-2">lock</i>
                                <input class='validate' type='password' name='password' id='password' />
                                <label for='password'>Enter your password</label>
                            </div>
                            <label style='float: right;'>
                                    <a class='red-text text-lighten-1' href='/reset_password/'><b>Forgot Password?</b></a>
                                </label>
                        </div>
    
                        <br />
                        <center>
                            <div class='row'>
                                <button type='submit' name='btn_login' class='col s12 btn btn-large waves-effect red lighten-2'>Login</button>
                            </div>
                        </center>
                    </form>
                </div>
            </div>
        </center>
    
    </main>
    <script>
        M.toast({
            html: "{{ field.label }} : {{ error }}",
            classes: 'red rounded',
            displatLength: 15000
        })
    </script>
    
    
        <script src="http://localhost:8000/static/js/materialize.min.js"></script>
        
        {% block extra_scripts %} {% endblock extra_scripts %} {% include 'user/messages.html' %}
    
    </body>
    
    </html>
    