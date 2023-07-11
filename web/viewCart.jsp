<%-- 
    Document   : viewCart
    Created on : Mar 1, 2023, 3:33:09 PM
    Author     : Admin
--%>

<%@page import="dao.PlantDAO"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section style="margin-top: 40px">
            <%
                String name = (String) session.getAttribute("name");
                if (name!=null) {
            %>
            <h3 style="margin: 1%">Welcome <%= session.getAttribute("name") %> come back</h3>
            <h3 style="margin: 1%"><a href="mainController?action=logout">Logout</a></h3>
            <h3 style="margin: 1%"><a href="personalPage.jsp">Personal Page</a></h3>
            <% }
            
            %>
            
            <h3><font style='color:red; margin: 1%'><%= (request.getAttribute("RESPONE")==null)?"":(String)request.getAttribute("RESPONE")%></font></h3>
            <table width="100%" class="order">
                <tr><td>Product ID</td><td>Image</td><td>Price</td><td>Quantity</td><td>Action</td></tr>
                <%
                    float total = 0;
                    HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                    if (cart != null && !cart.isEmpty()) {
                        Set<String> pids = cart.keySet();
                        for (String pid : pids) {
                            int quantity = cart.get(pid);
                            Plant plant = PlantDAO.getPlant(Integer.parseInt(pid));
                %>

                <form action="mainController" method = "post">
                    <tr><td><input type="hidden" value="<%=pid%>" name ="pid"><a href="getPlantServlet?pid=<%= pid%>"><%= pid %></a></td>
                        <td><img src="<%=plant.getImgpath()%>" class='plantimg'></td>
                        <td><%=plant.getPrice()%></td>
                        <td><input type="number" value="<%=quantity%>" name ="quantity" ></td>
                        <td><input type="submit" value ="update" name="action"/><input type="submit" value ="delete" name="action"/></td>
                    </tr>
                </form>
                <%
                    total=total+ plant.getPrice()* quantity;
                    }
                } else {
                %>
                <tr><td><font color='red'>Your cart is empty</font></td></tr>
                <%}
                %>
                <tr><td>Total money: <%= total %></td></tr>
                <tr><td>Order Date: <%= (new Date()).toString() %></td></tr>
                <tr><td>Ship Date: N/A</td></tr>
            </table>
        </section>
    <session>
        <form action="mainController" method="post" style="margin-bottom: 20px; margin: 1%">
            <input type="submit" value="Save Order" name="action" class="submitorder">
        </form>
    </session>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
