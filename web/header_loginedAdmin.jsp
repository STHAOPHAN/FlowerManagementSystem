<%-- 
    Document   : header_loginedAdmin
    Created on : Mar 4, 2023, 4:59:51 PM
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
        <header>
            <ul>
                <li><a href="mainController?action=manageinfo&object=accounts">Manage Accounts</a></li>
                <li><a href="mainController?action=manageinfo&object=orders">Manage Orders</a></li>
                <li><a href="mainController?action=manageinfo&object=plants">Manage Plants</a></li>
                <li><a href="mainController?action=manageinfo&object=categories">Manage Categories</a></li>
                <li>Welcome ${sessionScope.name} | <a href="mainController?action=logout">Logout</a></li>
            </ul>
        </header>
    </body>
</html>
