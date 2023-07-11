<%-- 
    Document   : index
    Created on : Feb 21, 2023, 12:02:35 AM
    Author     : Admin
--%>

<%@page import="dao.PlantDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Plant"%>
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
        <font style='color:red; margin-left: 60px'><%= (request.getAttribute("RESPONE")==null)?"":(String)request.getAttribute("RESPONE")%></font>
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list;
                String[] tmp = {"Out of stock", "Avaiable"};
                if (keyword == null && searchby == null) {
                    list = PlantDAO.getPlants("", "");
                } else {
                    list = PlantDAO.getPlants(keyword, searchby);
                }
                if (list != null && !list.isEmpty()) {
                    for (Plant p : list) {
            %>
            <table class='product'>
                <tr><td><img src='<%= p.getImgpath()%>' class='plantimg'/></td></tr>
                <tr><td>Product ID: <%= p.getId()%></td></tr>
                <tr><td>Product Name: <%= p.getName()%></td></tr>
                <tr><td>Price: <%= p.getPrice()%></td></tr>
                <tr><td>Status: <%= tmp[p.getStatus()]%></td></tr>
                <tr><td>Category: <%= p.getCatename()%></td></tr>
                <tr><td><a href="mainController?action=addtocart&pid=<%= p.getId()%>">Add to cart</a></td></tr>

            </table>
            <%}
                }
            %>         
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
