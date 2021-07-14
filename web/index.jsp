<%-- 
    Document   : index
    Created on : Jun 3, 2021, 4:59:45 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" class="fullscreen-bg">

    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/vendor/linearicons/style.css">

        <link rel="stylesheet" href="assets/css/main.css">
        <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
        <link rel="stylesheet" href="assets/css/demo.css">

        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
        <!-- ICONS -->
    </head>
    <style>
        .h1, h1 {
            font-size: 33px;
        }
        
    </style>
    <body>
        <!-- WRAPPER -->
        <div id="wrapper">
            <div class="vertical-align-wrap">
                <div class="vertical-align-middle">
                    <div class="auth-box ">
                        <div class="left">
                            <div class="content">
                                <div class="header">
                                    <h1 class="heading">Login to your account</h1>
                                </div>

                                <c:if test="${not empty requestScope.error}">
                                    <div class="alert alert-danger alert-dismissible" role="alert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                        <i class="fa fa-times-circle"></i> ${requestScope.error}
                                    </div>
                                </c:if>
                                <form class="form-auth-small" action="LoginController" method="post">
                                    <div class="form-group">
                                        <label for="signin-email" class="control-label sr-only">Email</label>
                                        <input type="text" class="form-control" id="signin-email" name="txtUsername" value="${empty requestScope.id ? '' : requestScope.id}" placeholder="Username">
                                    </div>
                                    <div class="form-group">
                                        <label for="signin-password" class="control-label sr-only">Password</label>
                                        <input type="password" class="form-control" id="signin-password" name="txtPassword" value="" placeholder="Password">
                                    </div>
                                    <input type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="Login">
                                    <br>

                                </form>
                            </div>
                        </div>
                        <div class="right">
                            <div class="overlay"></div>
                            <div class="content text">
                                <h1 class="heading">User Management</h1>
                            </div>
                        </div>
                        <div class="clearfix"></div>

                    </div>

                </div>
            </div>
        </div>
        <!-- END WRAPPER -->
    </body>

</html>
