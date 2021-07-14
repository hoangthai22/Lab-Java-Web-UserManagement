<%-- 
    Document   : test
    Created on : Jun 4, 2021, 3:47:48 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">

    <head>

        <title>Insert</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
        <!-- MAIN CSS -->
        <link rel="stylesheet" href="assets/css/main.css">
        <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
        <link rel="stylesheet" href="assets/css/demo.css">
        <!-- GOOGLE FONTS -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
        <!-- ICONS -->
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
    </head>
    <style type="text/css">
        #testForm label.error{
            color: red;
            width: 250px;
            font-style: italic;
        }
    </style>
    <body>
        <!-- WRAPPER -->
        <div id="wrapper">
            <jsp:include page="menu.jsp"></jsp:include>
                <div id="sidebar-nav" class="sidebar">
                    <div class="sidebar-scroll">
                        <nav>
                            <ul class="nav">
                            <c:if test="${sessionScope.user.roleId == 'MA'}">
                                <li> <a href="#subPages" data-toggle="collapse" class=""><i
                                            class="lnr lnr-home"></i> <span>Manage User</span> <i
                                            class="icon-submenu lnr lnr-chevron-left"></i></a>
                                    <div id="subPages" class="collapse in">
                                        <ul class="nav">
                                            <li><a href="GetAllUserController?action=GetAll" class="">All</a></li>
                                            <li><a href="GetAllUserController?action=GetMA" class="">Manager</a></li>
                                            <li><a href="GetAllUserController?action=GetEM" class="">Employee</a></li>
                                        </ul>
                                    </div></li>
                                <li><a href="GetHistoryController" class=""><i class="lnr lnr-list"></i>
                                        <span>Manage promotion</span></a></li>
                                    </c:if>
                        </ul>
                    </nav>  
                </div>
            </div>  

            <div class="main">
                <!-- MAIN CONTENT -->
                <div class="main-content">
                    <div class="container-fluid">

                        <div class="row">
                            <div class="col-md-8">
                                <!-- PANEL HEADLINE -->
                                <div class="panel panel-headline">

                                    <form action="InsertUserController" method="post" id="testForm"
                                          enctype="multipart/form-data">

                                        <div class="panel-body">
                                            <h2 class="page-title" style="text-align: center">Insert User</h2>
                                            <c:if test="${not empty requestScope.error}">
                                                <div class="alert alert-danger alert-dismissible" role="alert">
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                    <i class="fa fa-times-circle"></i> ${requestScope.error}
                                                </div>
                                            </c:if>
                                            <span>UserName: </span>
                                            <input class="form-control input-lg" type="text" name="txtUsername" value="${not empty requestScope.txtUsername ? txtUsername : ''}">
                                            <br> 
                                            <span>Password: </span>
                                            <input class="form-control input-lg" type="text"  name="txtPassword">
                                            <br>
                                            <span>Confirm Password: </span>
                                            <input class="form-control input-lg" type="text"  name="txtPasswordConfirm">
                                            <br>
                                            <span>Full name: </span>
                                            <input class="form-control input-lg" type="text"  name="txtName" value="${not empty requestScope.dto ? dto.fullName : ''}">
                                            <br>
                                            <span>Email: </span>
                                            <input class="form-control input-lg" type="email"  name="txtEmail" value="${not empty requestScope.dto ? dto.userEmail : ''}">
                                            <br>
                                            <span>Phone number: </span>
                                            <input class="form-control input-lg" type="number"  name="txtPhone" value="${not empty requestScope.dto ? dto.userPhone : ''}">
                                            <br>
                                            <span>Role: </span>
                                            <select class="form-control" style="height: 46px" name="txtRole">
                                                <option ${requestScope.dto.roleId == 'MA' ? 'selected=""' : ''}value="MA">Manager</option>
                                                <option ${requestScope.dto.roleId == 'EM' ? 'selected=""' : ''}value="EM">Employee</option>
                                            </select>

                                            <br>
                                            <span>Image: </span>

                                            <input type = "file" name = "fileup" value="" style="width: 100%;" />
                                            <br>
                                            <input type="submit" value="Insert" name="action" class="btn btn-success" style="margin-left: 900px; width: 100px; height: 40px;"/>
                                        </div>
                                    </form>


                                </div>
                                <!-- END PANEL HEADLINE -->
                            </div>

                        </div>


                    </div>
                </div>
                <!-- END MAIN CONTENT -->
            </div>
            <!-- END MAIN -->
            <div class="clearfix"></div>
            
        </div>
        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script type="text/javascript" src="assets/js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="assets/js/jquery.validate.js"></script>
        <script type="text/javascript" src="assets/js/additional-methods.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/scripts/klorofil-common.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#testForm").validate({
                    rules: {
                        txtUsername: {
                            required: true,
                            rangelength: [6, 20]
                        },
                        txtPassword: {
                            required: true,
                            rangelength: [6, 20]
                        },
                        txtPasswordConfirm: {
                            required: true,
                            rangelength: [6, 20]
                          
                        },
                        txtName: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtEmail: {
                            required: true,
                            email: true
                        },
                        txtPhone: {

                            required: true
                        },
                        fileup: {
                            required: true

                        }
                    }
                });
            });
        </script>
    </body>

</html>
