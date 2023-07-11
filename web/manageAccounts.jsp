<%-- 
    Document   : manageAccounts
    Created on : Mar 4, 2023, 5:21:50 PM
    Author     : Admin
--%>

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
            <form action="mainController?object=accounts" method="post">
                <input type="text" name="txtsearch">
                <input type="submit" value="Search" name="action">
            </form>
            <h1></h1>
            <table class="order">
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>phone</th>
                    <th>Status</th>
                    <th>Role</th>
                    <th>Token</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="acc" items="${requestScope.list}">
                    <tr>
                        <td><c:out value="${acc.getAccID()}"></c:out></td>
                        <td><c:out value="${acc.getEmail()}"></c:out></td>
                        <td><c:out value="${acc.getPassword()}"></c:out></td>
                        <td><c:out value="${acc.getFullName()}"></c:out></td>
                        <td><c:out value="${acc.getPhone()}"></c:out></td>
                            <td>
                            <c:choose>
                                <c:when test="${acc.getStatus() eq 1}">Active</c:when>
                                <c:otherwise>Inactive</c:otherwise>
                            </c:choose>
                        </td>
                            <td>
                            <c:choose>
                                <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                                <c:otherwise>User</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${acc.getToken()}"></c:out></td>
                        <td>
                            <c:if test="${acc.getRole() eq 0}">
                                <c:url var="mylink" value="mainController">
                                    <c:param name="email" value="${acc.getEmail()}"></c:param>
                                    <c:param name="status" value="${acc.getStatus()}"></c:param>
                                    <c:param name="action" value="updateStatusAccount"></c:param>
                                </c:url>
                                <a href="${mylink}">Block/Unblock</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
