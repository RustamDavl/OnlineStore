<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.08.2022
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>
<form action="${pageContext.request.contextPath}/deleteGoods" method="post">

    <label for="nameId">Product name :
        <input type="text" name="productName" id="nameId">
    </label><br>

    <button type="submit">DELETE</button><br>

    <a href="${pageContext.request.contextPath}/adminHomepage"><button type="button">main page</button></a>
    <c:if test="${!requestScope.errors.isEmpty()}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">

                <p>
                        ${error}
                </p>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${!requestScope.deleted.isEmpty()}">
        <div style="color: green;">
            <c:forEach var="s" items="${requestScope.deleted}">
                <p>${s}</p>
            </c:forEach>
        </div>
    </c:if>

</form>

</body>
</html>
