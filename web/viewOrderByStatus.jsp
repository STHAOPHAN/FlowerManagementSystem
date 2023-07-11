<%-- 
    Document   : viewOrderByStatus
    Created on : Mar 5, 2023, 6:09:34 PM
    Author     : Admin
--%>

<%@page import="dao.AccountDAO"%>
<%@page import="dto.Account"%>
<%@page import="dao.OrderDAO"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet" href="mycss.css" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");

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
        <%
                    }
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
            <h3>Welcome <%=name%> come back</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section>
            <%
                int statustosearch = Integer.parseInt(request.getParameter("status"));
                ArrayList<Order> list = OrderDAO.getOrders(email, statustosearch);
                String[] status = {"", "processing", "completed", "canceled"};
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {
            %>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order Status</td><td>action</td></tr>
                <tr>
                    <td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%></td>
                    <td><a href="OrderDetail.jsp?orderid=<%= ord.getOrderID()%>">Detail</a>
                        <%if (ord.getStatus() == 3) {
                        %>
                        <a href="mainController?action=reorder&status=1&orderid=<%=ord.getOrderID()%>">Re-Order</a></td>
                        <%}%>
                        <%if (ord.getStatus() == 1) {
                        %>
                <a href="mainController?action=reorder&status=3&orderid=<%=ord.getOrderID()%>&">Cancel</a></td>
                <%}%>
                </tr>
            </table>
            <%
                }
            } else {
            %>
            <p>You don't have any order</p>
            <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }
        %>
    </body>
</html>

