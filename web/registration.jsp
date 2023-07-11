<%-- 
    Document   : registration
    Created on : Feb 21, 2023, 12:06:25 AM
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
            <%@include file="header.jsp" %>
        </header>
        <section style="margin: 40px 0px 20px 0px">
            <form action="mainController" method="post" class="formregister">
                <h1>Register</h1>
                <table>
                    <tr><td>Email</td><td><input type="text" name="txtemail" required="" pattern="^[a-zA-Z0-9._%+-]+@gmail\.com$"></td></tr>
                    <tr><td>Full name</td><td><input type="text" name="txtfullname" required=""></td></tr>
                    <tr><td>Password</td><td><input type="text" name="txtpassword" required=""></td></tr>
                    <tr><td>Phone</td><td><input type="text" name="txtphone" value="<%= (request.getAttribute("txtphone")==null)?"":request.getAttribute("txtphone")%>" required="" pattern="^\d+$"></td></tr>
                    <tr><td colspan="2"><input type="submit" value="register" name="action"></td></tr>
                </table>
            </form>            
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
