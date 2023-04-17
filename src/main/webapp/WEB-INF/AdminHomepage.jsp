<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 08.08.2022
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>

<form>

    <a href="${pageContext.request.contextPath}/addGoods">
        <button type="button">add goods</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/deleteGoods">
        <button type="button">delete goods</button>
    </a>

    <a href="${pageContext.request.contextPath}/addExisting">
        <button type="button">add existing product</button>
    </a>
    <br>
    <a href="${pageContext.request.contextPath}/allUsers"><button type="button">show buyers</button></a>


</form>

</body>
</html>
