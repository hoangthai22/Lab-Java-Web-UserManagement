<%-- 
    Document   : menu
    Created on : Jun 3, 2021, 10:47:36 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


    </head>
    <body>
        <!-- NAVBAR -->

        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="brand">
                <label>User Management</label> 
            </div>
            <div class="container-fluid">
                <form class="navbar-form navbar-left">
                    <div class="success" style="margin-left: 1280px;">
                        <c:if test="${sessionScope.user.roleId == 'MA'}">
                            <a href="insert.jsp" class="btn btn-success"  style="width: 105px">Insert User</a>
                        </c:if>
                    </div>
                </form>
                <div id="navbar-menu">
                    <ul class="nav navbar-nav navbar-right">

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="${sessionScope.user.userImg}" class="img-circle" alt="Avatar"> <span>${sessionScope.user.userId}</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="ShowInfoController?txtId=${sessionScope.user.userId}"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
                                <li><a href="LogoutController"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- END NAVBAR -->
        <!-- LEFT SIDEBAR -->
       




    </body>
</html>
