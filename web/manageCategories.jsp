

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
            <form action="mainController?object=categories" method="post">
                <input type="text" name="txtsearch">
                <input type="submit" value="Search" name="action">
            </form>
            <h1></h1>
            <table class="order">
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Action</th>
                </tr>
                <font color='red'>${requestScope.RESPONE}</font>
                <form action="mainController?object=categories" method="post">
                    <tr>
                        <td></td>
                        <td><input type="text" name="txtcatename" required=""></td>
                        <td><input type="submit" value="Create" name="action"</td>
                    </tr>
                </form>                
                <c:forEach var="acc" items="${requestScope.list}">
                    <tr>
                        <td><c:out value="${acc.getCateid()}"></c:out></td>
                        <td><c:out value="${acc.getCatename()}"></c:out></td>
                            <td>
                            <c:url var="mylink" value="updateInfo.jsp">
                                <c:param name="cateid" value="${acc.getCateid()}"></c:param>
                                <c:param name="object" value="categories"></c:param>
                            </c:url>
                            <a href="${mylink}">Update</a>  
                            <c:url var="mylink" value="mainController">
                                <c:param name="action" value="deleteobj"></c:param>
                                <c:param name="cateid" value="${acc.getCateid()}"></c:param>
                                <c:param name="object" value="categories"></c:param>
                            </c:url>
                            <a href="${mylink}">Delete</a> 
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
