<%-- 
    Document   : updateInfo
    Created on : Mar 17, 2023, 3:51:47 PM
    Author     : Admin
--%>

<%@page import="dto.Category"%>
<%@page import="dao.PlantDAO"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <section>
            <%
                String obj = request.getParameter("object");
                if (obj.equals("plants")) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    Plant plant = PlantDAO.getPlant(pid);
                    session.setAttribute("plant", plant);
                    ArrayList<Category> list = PlantDAO.getCategory();
                    String status = "";
                    if (plant.getStatus() == 1) {
                        status = "Available";
                    } else {
                        status = "Not Available";
                    }
            %>
            <font color='red'>${requestScope.RESPONE}</font>
            <table class="order" style="text-align: left">
                <tr><td></td><td>Original Information</td><td>Edit</td><td></td></tr>
                <form action="mainController?object=plants&pid=<%=pid%>" method="post"> 
                    <tr><td>Plant ID</td><td><%=pid%></td><td></td><td><input type="submit" value="Update Info" name="action"></td></tr>
                    <tr><td>Plant Name</td><td><%=plant.getName()%></td><td><input type="text" name="txtpname"></td></tr>
                    <tr><td>Price</td><td><%=plant.getPrice()%></td><td><input type="text" name="txtprice" pattern="^\d+$"></td></tr>
                    <tr><td>Image Path</td><td><%=plant.getImgpath()%></td><td><input type="text" name="txtimgpath"></td></tr>
                    <tr><td>Description</td><td><%=plant.getDescription()%></td><td><input type="text" name="txtdes"></td></tr>
                    <tr>
                        <td>Status</td><td><%=status%></td>
                        <td>
                            <select name="txtstatus">
                                <option value="1">Available</option>
                                <option value="0">Not Available</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Cate Name</td>
                        <td><%=plant.getCatename()%></td>
                        <td>
                            <select name="txtcateid">
                                <c:forEach var="cate" items="<%=list%>">
                                    <option value="${cate.getCateid()}">${cate.getCatename()}</option>
                                </c:forEach>
                                <</select>
                        </td>
                    </tr>
                </form>
            </table>
                        <%} else if (obj.equals("categories")){
                    int cateid = Integer.parseInt(request.getParameter("cateid"));
                    Category cate = PlantDAO.getCategory(cateid);
                    session.setAttribute("category", cate);
            %>
            <font color='red'>${requestScope.RESPONE}</font>
            <table class="order" style="text-align: left">
                <tr><td></td><td>Original Information</td><td>Edit</td><td></td></tr>
                <form action="mainController?object=categories&cateid=<%=cateid%>" method="post"> 
                    <tr><td>Cate ID</td><td><%=cateid%></td><td></td><td><input type="submit" value="Update Info" name="action"></td></tr>
                    <tr><td>Cate Name</td><td><%=cate.getCatename()%></td><td><input type="text" name="txtcatename"></td></tr>

                </form>
            </table>
                        <%}%>
        </section>        
    </body>
</html>
