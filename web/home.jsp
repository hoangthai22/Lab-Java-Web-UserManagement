<%-- 
    Document   : home
    Created on : Jun 3, 2021, 10:46:12 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>

    <head>
        <title>Home</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0,
              maximum-scale=1.0, user-scalable=0">
        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet"
              href="assets/vendor/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/vendor/linearicons/style.css">
        <!-- MAIN CSS -->
        <link rel="stylesheet" href="assets/css/main.css">
        <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
        <link rel="stylesheet" href="assets/css/demo.css">
        <!-- GOOGLE FONTS -->
        <link
            href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
            rel="stylesheet">
        <!-- ICONS -->
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
    </head>

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
                            <div class="col-md-12">
                                <c:if test="${not empty requestScope.error}">
                                    <div class="alert alert-danger alert-dismissible" role="alert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                        <i class="fa fa-times-circle"></i> ${requestScope.error}
                                    </div>
                                </c:if>
                                <!-- TABLE STRIPED -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">List of User</h3>
                                    </div>
                                    <form action="AddToPromotionController" method="post">

                                        <input type="text" name="txtSearchByName" value="" style="margin-left: 26px;  width: 300px "/>
                                        <input type="submit" name="action" value="Search" class="btn-inverse" style="  width: 80px;"/>
                                        <br>

                                        <input type="submit" name="action" value="Delete" onclick="Confirm()" class="btn btn-danger" style="margin-right: 27px;float: right;"/>
                                        <input type="hidden" name="page" value="${requestScope.page}"/>
                                        <div class="panel-body">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Image</th>
                                                        <th>User ID</th>
                                                        <th>Full Name</th>
                                                        <th>Email</th>
                                                        <th>Phone Number</th>
                                                        <th>Role</th>
                                                        <th>Action</th>
                                                        <th>#</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach items="${requestScope.listUser}" var="i" varStatus="counter">

                                                        <tr>

                                                            <td>${counter.count}</td>
                                                            <td><img src="${i.userImg}" style="max-width: 50px;"
                                                                     class="img-circle"
                                                                     alt="Avatar"></td>
                                                            <td>${i.userId}</td>
                                                            <td>${i.fullName}</td>
                                                            <td>${i.userEmail}</td>
                                                            <td>${i.userPhone}</td>
                                                            <td>${i.roleId == 'MA' ? 'Manager' : 'Employee'}</td>

                                                            <td style="width: 300px;"> 
                                                                <a href="ShowInfoController?txtId=${i.userId}" class="btn btn-warning" style="width: 80px;">Edit</a>
                                                                <a href="DeleteUserController?txtId=${i.userId}&page=${requestScope.page}"  onclick="Confirm()" class="btn btn-danger" ${i.userId == sessionScope.user.userId ? 'disabled=""' : ''} style="margin-left: 10px;">Delete</a>
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" id="Cbox" onclick="" style="width: 20px;
                                                                       height: 20px" name="txtCheck" ${i.checked ? 'checked' : ''} value="${i.userId}" ${i.userId == sessionScope.user.userId ? 'disabled=""' : ''}/>
                                                            </td>

                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            <input type="submit" name="action" value="Add to Promotion List" class="btn btn-success" style="float: right; height: 40px;width: 170px;"/>

                                        </div>
                                    </form>
                                </div>

                                <!-- END TABLE STRIPED -->
                            </div>

                        </div>

                    </div>
                </div>
                <!-- END MAIN CONTENT -->

            </div>
            <!-- END MAIN -->
            <div class="clearfix"></div>

        </div>
        <script src="assets/vendor/jquery/jquery.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/scripts/klorofil-common.js"></script>
        <script src="assets/js/main.js"></script>
    </body>

</html>


