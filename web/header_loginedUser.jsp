<%-- 
    Document   : header_loginedUser.jsp
    Created on : Feb 21, 2023, 12:26:50 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="changeProfileUser.jsp">Change Profile</a></li>
                <li><a href="mainController?action=viewcartbystatus&status=2">Completed Orders</a></li>
                <li><a href="mainController?action=viewcartbystatus&status=3">Canceled Orders</a></li>
                <li><a href="mainController?action=viewcartbystatus&status=1">Processing Orders</a></li>
                <li><form action="personalPage.jsp">from <input type="date"name="from"> to <input type="date"name="to">
                        <input type="submit"value="Search"></form>
                </li>
            </ul>
        </nav>
    </body>
</html>
