<%-- 
    Document   : viewPlant
    Created on : Mar 4, 2023, 3:58:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link  rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <jsp:useBean id="plantObj" class="dto.Plant" scope="request"/>
        <table style="margin: 1%; padding-top: 40px">
            <tr><td rowspan="8"><img style="height: 500px; width: 500px" src="<jsp:getProperty name="plantObj" property="imgpath"></jsp:getProperty>"></td></tr>
            <tr><td>ID Plant: <span style="color: red; "><jsp:getProperty name="plantObj" property="id" ></jsp:getProperty></span></td></tr>
            <tr><td>Product Name: <span style="color: red;"><jsp:getProperty name="plantObj" property="name"></jsp:getProperty></span></td></tr>
            <tr><td>Price: <span style="color: red;"><jsp:getProperty name="plantObj" property="price"></jsp:getProperty></span></td></tr>
            <tr><td>Description: <span style="color: red;"><jsp:getProperty name="plantObj" property="description"></jsp:getProperty></span></td></tr>
            <tr><td>Status: <span style="color: red;"><jsp:getProperty name="plantObj" property="status"></jsp:getProperty></span></td></tr>
            <tr><td>Cate id: <span style="color: red;"><jsp:getProperty name="plantObj" property="cateid"></jsp:getProperty></span></td></tr>
            <tr><td>Category: <span style="color: red;"><jsp:getProperty name="plantObj" property="catename"></jsp:getProperty></span></td></tr>
            </table>
            <a style='margin: 1%' href="viewCart.jsp">Back</a>

            <!-- su dung EL-->
            <!--
            <table>
                <tr><td rowspan="8"><img src="${plantObj.imgpath}"></td></tr>
            <tr><td>id:${plantObj.id}</td></tr>
            <tr><td>product name:${plantObj.name}</td></tr>
            <tr><td>price:${plantObj.price}</td></tr>
            <tr><td>description:${plantObj.description}</td></tr>
            <tr><td>status:${plantObj.status}</td></tr>
            <tr><td>cate id:${plantObj.cateid}</td></tr>
            <tr><td>category:${plantObj.catename}</td></tr>           
            </table>
        -->
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
