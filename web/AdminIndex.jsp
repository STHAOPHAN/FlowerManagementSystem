<%-- 
    Document   : AdminIndex
    Created on : Mar 4, 2023, 4:43:24 PM
    Author     : Admin
--%>

<%@page import="dao.AccountDAO"%>
<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            String role = (String)session.getAttribute("role");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie acookie : c) {
                    if (acookie.getName().equals("selector")) {
                        token = acookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null && acc.getRole() == 1) {
                            name = acc.getFullName();
                            session.setAttribute("name", name);
                            email = acc.getEmail();
                            login = true;
                        } else {%>
        <p><font color='red'>you must login to view admin page</font></p>
        <p><a href="index.jsp">Return main page</a></p>   
        <%}
                }
            }
        %>

        <%        }
            if (login || role.equals("1")) {
        %>
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <section class="right">
                <img src="images/background.jpg"/>
            </section>
        <%}%>            
    </body>
</html>
