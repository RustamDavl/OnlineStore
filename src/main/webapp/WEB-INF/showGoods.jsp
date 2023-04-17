<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.08.2022
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<%@include file="Logout.jsp"%>

<form action="${pageContext.request.contextPath}/goods" method="post">
    <ul>
        <c:forEach var="product" items="${requestScope.goods}">
            <li>


                <img src="${pageContext.request.contextPath}/images/${product.getImagePath()}" alt="there is no pict :("
                     height="100" width="100"><br>
                <a href="${pageContext.request.contextPath}/buyThisGood?prodName=${product.getProductInfo().getName()}">${product.getProductInfo().getDescription()}</a>


            </li>
        </c:forEach>
    </ul>


    <input type="hidden" name="offsetLimit" value="3">


    <button type="submit">show more</button>
    <br>
    <a href="${pageContext.request.contextPath}/buyerHomepage">
        <button type="button">main page</button>
    </a>
</form>


</body>
</html>
