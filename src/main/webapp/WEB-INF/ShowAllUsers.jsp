<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.08.2022
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>
<c:if test="${not empty requestScope.listOfUsers}">
    <ul>
    <c:forEach var="buyer" items="${requestScope.listOfUsers}">
        <li>
            <c:forEach var="id" items="${requestScope.id}">
                <a href="${pageContext.request.contextPath}/showOrderForAdmin?id=${id.getId()}">${buyer}</a>
            </c:forEach>

        </li>
    </c:forEach>
    </ul>
</c:if>

</body>
</html>
