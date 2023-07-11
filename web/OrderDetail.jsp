<%-- 
    Document   : OrderDetail
    Created on : Feb 23, 2023, 12:00:26 AM
    Author     : Admin
--%>

<%@page import="dao.OrderDAO"%>
<%@page import="dto.OrderDetail"%>
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
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <h3>Logout</h3>
            <h3><a href="personalPage.jsp">View all orders</a></h3>
        </section>
        <section>
            <%
                String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail detail : list) {
            %>
            <table class='order'>
                <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Price</td><td>Quantity</td></tr>
                <tr>
                    <td><%= detail.getOrderID()%></td>
                    <td><%= detail.getPlantID()%></td>
                    <td><%= detail.getPlantName()%></td>
                    <td><img src='<%= detail.getImgPath()%>' class='plantimg'/></td>
                    <td><%= detail.getPrice()%></td>
                    <td><%= detail.getQuantity()%></td>
                    <% money = money + detail.getPrice() * detail.getQuantity();%>
                </tr>
            </table>
            <%}//end loop%>
            <h3>Total money: <%= money%></h3>
            <%} else {
            %>
            <p>You don't have any order</p>
            <%}
            }%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
