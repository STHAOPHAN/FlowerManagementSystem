

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
        <section>
            <c:import url="header_loginedAdmin.jsp"/>
            <form action="mainController?object=plants" method="post">
                <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null) ? "" : request.getParameter("txtsearch")%>">
                <select name="searchby">
                    <option value="byname">by name</option>
                    <option value="bycate">by category</option>
                </select>
                <input type="submit" value="Search" name="action" >
            </form>
            <h1></h1>
            <table class="order">
                <tr>
                    <th>Plant ID</th>
                    <th>Plant Name</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Action</th>
                </tr>
                <form action="mainController?object=plants" method="post">
                    <tr>
                        <td></td>
                        <td><input type="text" name="txtpname" required=""></td>
                        <td><input type="text" name="txtprice" required="" pattern="^\d+$"></td>
                        <td><input type="text" name="txtimgpath" required=""></td>
                        <td><input type="text" name="txtdes"></td>
                        <td>
                            <select name="txtpstatus">
                                <option value="1">Available</option>
                                <option value="0">Not Available</option>
                            </select>
                        </td>
                    <font color='red'>${requestScope.RESPONE}</font>
                    <td>
                        <select name="txtcateid">
                            <c:forEach items="${requestScope.catelist}" var="plant">
                                <option value="${plant.getCateid()}">${plant.getCateid()}: ${plant.getCatename()}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td><input type="submit" value="Create" name="action"</td>
                    </tr>
                </form>                
                <c:forEach var="plant" items="${requestScope.list}">
                    <tr>
                        <td><c:out value="${plant.getId()}"></c:out></td>
                        <td><c:out value="${plant.getName()}"></c:out></td>
                        <td><c:out value="${plant.getPrice()}"></c:out></td>
                        <td><img src="${plant.getImgpath()}" class="plantimg"></td>
                        <td><c:out value="${plant.getDescription()}"></c:out></td>
                            <td>
                            <c:choose>
                                <c:when test="${plant.getStatus() eq 1}">Available</c:when>
                                <c:otherwise>Not Available</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${plant.getCateid()}"></c:out></td>
                        <td><c:out value="${plant.getCatename()}"></c:out></td>
                            <td>
                            <c:url var="mylink" value="updateInfo.jsp">
                                <c:param name="pid" value="${plant.getId()}"></c:param>
                                <c:param name="object" value="plants"></c:param>
                            </c:url>
                            <a href="${mylink}">Update</a>     
                            <c:url var="mylink" value="mainController">
                                <c:param name="action" value="deleteobj"></c:param>
                                <c:param name="pid" value="${plant.getId()}"></c:param>
                                <c:param name="object" value="plants"></c:param>
                            </c:url>
                            <a href="${mylink}">Delete</a>    
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
