<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        body#LoginForm {
            background-image: url("https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg");
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            padding: 10px;
        }

        .container {
            margin: 50px auto;
        }

        .panel h2 {
            color: #444444;
            font-size: 18px;
            margin: 0 0 8px 0;
        }

        .panel p {
            color: #777777;
            font-size: 14px;
            margin-bottom: 30px;
            line-height: 24px;
        }

        .login-form .form-control {
            background: #f7f7f7 none repeat scroll 0 0;
            border: 1px solid #d4d4d4;
            border-radius: 4px;
            font-size: 14px;
            height: 50px;
            line-height: 20px;
        }

        .main-div {
            background: #ffffff none repeat scroll 0 0;
            border-radius: 2px;
            margin: 10px auto 130px;
            max-width: 38%;
            padding: 50px 70px 70px 70px;
        }

        .login-form .form-group {
            margin-bottom: 10px;
        }

        .login-form {
            text-align: center;
        }

        .login-form .btn.btn-primary {
            background: #f0ad4e none repeat scroll 0 0;
            border-color: #f0ad4e;
            color: #ffffff;
            font-size: 14px;
            width: 100%;
            height: 50px;
            line-height: 50px;
            padding: 0;
        }

        .login-form .btn.btn-primary.reset {
            background: #ff9900 none repeat scroll 0 0;
        }

        .back a {
            color: #444444;
            font-size: 13px;
            text-decoration: none;
        }
    </style>
</head>
<body id="LoginForm">
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h2>Login</h2>
                <p>Please enter your username and password</p>
            </div>
            <form:form id="Login" action="/validate" modelAttribute="userAttribute" method="post">

                <div class="form-group">

                    <form:input class="form-control" path="username" type="text" placeholder="Username"/>
                </div>

                <div class="form-group">

                    <form:password class="form-control" path="password" placeholder="Password"/>

                </div>
                <button type="submit" class="btn btn-primary">Login</button>

            </form:form>
        </div>
    </div>
</div>
</div>


</body>
</html>
