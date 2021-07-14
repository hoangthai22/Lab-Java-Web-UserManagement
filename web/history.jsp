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
        <title>History</title>
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
                            <div class="col-md-3">
                                <!-- PANEL DEFAULT -->
                                <div class="panel">
                                    <div class="panel-heading"> 
                                        <h3 class="panel-title">Edit</h3>
                                        <div class="right">
                                            <button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>


                                        </div>

                                    </div>
                                    <div class="panel-body">
                                        <form action="ActionHisoryController?action=EditHistory" method="post">
                                            <span>Full name: </span>
                                            <input class="form-control" type="text" id="userid" name="txtIdEdit" value="${requestScope.txtIdEdit}" readonly=""/>
                                            <br>
                                            <span>Rank </span>
                                            <input class="form-control" type="number" id="rank" name="txtRankEdit" value="${requestScope.txtRankEdit}"/>
                                            <br>
                                            <button type="submit" class="btn btn-warning" style="float: right">Ok</button>
                                        </form>
                                    </div>
                                </div>
                                <!-- END PANEL DEFAULT -->
                            </div>
                            <div class="col-md-12">

                                <!-- TABLE STRIPED -->
                                <div class="panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">List of User</h3>

                                        <div class="panel-body">
                                            <c:if test="${not empty requestScope.removeSuccess}">
                                                <div class="alert alert-success alert-dismissible" role="alert" >
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                    <i class="fa fa-check-circle"></i> 
                                                    <label> ${requestScope.removeSuccess}</label>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty requestScope.updateSuccess}">
                                                <div class="alert alert-success alert-dismissible" role="alert" >
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                    <i class="fa fa-check-circle"></i> 
                                                    <label> ${requestScope.updateSuccess}</label>
                                                </div>
                                            </c:if>

                                            <c:if test="${not empty requestScope.error}">
                                                <div class="alert alert-danger alert-dismissible" role="alert" >
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                    <i class="fa fa-times-circle"></i> 
                                                    <label> ${requestScope.error}</label>
                                                </div>
                                            </c:if>

                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Image</th>
                                                        <th>User ID</th>
                                                        <th>Role</th>
                                                        <th>Rank</th>
                                                        <th>Date added</th>
                                                        <th>Action</th>

                                                    </tr>
                                                </thead>

                                                <tbody>

                                                    <c:forEach varStatus="counter" items="${requestScope.listHistory}" var="i">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td><img src="${i.userImg}" style="max-width: 50px;"
                                                                     class="img-circle"
                                                                     alt="Avatar"></td>
                                                            <td><input type="hidden" value="${i.userId}" id="idEdit${counter.count}"/>${i.userId}</td>
                                                            <td>${i.roleId == 'MA' ? 'Manager' : 'Employee'}</td>
                                                            <td><input type="hidden" id="rankEdit${counter.count}" value="${i.promotionRank}"/>${i.promotionRank}</td>
                                                            <td>${i.promotionDate}</td>
                                                            <td style="width: 300px;"> 
                                                                <a href="ActionHisoryController?action=DeleteHistory&txtId=${i.userId}"  onclick="Confirm()" class="btn btn-danger">Delete</a>
                                                                <a id="click${counter.count}"  onclick="Edit(${counter.count}, ${requestScope.listHistory.size()})" class="btn btn-warning">Edit</a>
                                                                <input type="hidden" id="total" value="${requestScope.listHistory.size()}"/>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>

                                            </table>

                                        </div>

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


