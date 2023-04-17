<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.08.2022
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${not empty requestScope.orders}">

    <ul>
        <c:forEach var="order" items="${requestScope.orders}">
            <li>
                ${order}
            </li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
