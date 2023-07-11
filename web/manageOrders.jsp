

<%@page import="dao.AccountDAO"%>
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
            <table>
                <tr><td>
                        <form action="personalPage.jsp">from <input type="date"name="from"> to <input type="date"name="to"><input type="hidden" name="searchby" value="admin">
                            <input type="submit"value="Search">
                        </form>                    
                    </td>
                    <td></td><td></td><td></td>
                    <td>
                        <form action="mainController?object=orders" method="post">
                            <input type="text" name="txtsearch">
                            <input type="submit" value="Search" name="action">
                        </form>                    
                    </td></tr>
            </table>
            <table class="order">
                <tr>
                    <th>Email</th>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Ship Date</th>
                    <th>Status</th>
                    <th>Account ID</th>
                </tr>
                <c:forEach var="acc" items="${requestScope.list}">
                    <tr>
                        <td><c:out value="${AccountDAO.getEmail(acc.getAccID())}"></c:out></td>
                        <td><c:out value="${acc.getOrderID()}"></c:out></td>
                        <td><c:out value="${acc.getOrderDate()}"></c:out></td>
                        <td><c:out value="${acc.getShipDate()}"></c:out></td>
                            <td>
                            <c:choose>
                                <c:when test="${acc.getStatus() eq 1}">Processing</c:when>
                                <c:when test="${acc.getStatus() eq 2}">Completed</c:when>
                                <c:otherwise>Canceled</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${acc.getAccID()}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
    </body>
</html>
