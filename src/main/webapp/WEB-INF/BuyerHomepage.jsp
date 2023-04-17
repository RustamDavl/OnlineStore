<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 08.08.2022
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<%@include file="Logout.jsp"%>

    <a href="${pageContext.request.contextPath}/goods"> <button type="button">Buy goods</button></a><br>
    <a href="${pageContext.request.contextPath}/myGoods"><button type="button">My goods</button></a>




</body>
</html>
