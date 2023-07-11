<%-- 
    Document   : changeProfileUser
    Created on : Mar 7, 2023, 3:14:30 PM
    Author     : Admin
--%>

<%@page import="dao.AccountDAO"%>
<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link  rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            String phone = (String) session.getAttribute("phone");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie acookie : c) {
                    if (acookie.getName().equals("selector")) {
                        token = acookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullName();
                            email = acc.getEmail();
                            login = true;
                        } else {%>
        <p><font color='red'>you must login to view personal page</font></p>
        <p><a href="index.jsp">Return main page</a></p>   
        <%}
                }
            }
        %>

        <%        } else
                login = true;
            if (login) {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <table class="order" style="text-align: left">
                <tr><td></td><td>Original Infomation</td><td>Edit</td><td></td></tr>
                <tr><td>Email</td><td><%=email%></td></tr>
                <form action="mainController?email=<%=email%>" method = "post" class="formlogin">
                    <tr><td>FullName</td><td><%=name%></td><td><input type="text" name ="txtfullname" required=""></td>
                        <td><input type="submit" value ="Change" name="action"/></td></tr>
                </form>
                <form action="mainController?email=<%=email%>" method = "post" class="formlogin">
                    <tr><td>Phone</td><td><%=phone%></td><td><input type="text" name ="txtphone" pattern="^\d+$" required=""></td>
                        <td><input type="submit" value ="Change" name="action"/></td></tr>
                </form>
                <font style='color:red;'><%= (request.getAttribute("RESPONE") == null) ? "" : (String) request.getAttribute("RESPONE")%></font>
            </table>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }
        %>       
    </body>
</html>
