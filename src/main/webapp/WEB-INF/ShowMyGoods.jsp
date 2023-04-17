<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 17.08.2022
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>

<form action="${pageContext.request.contextPath}/myGoods" method="post">
    <p>My orders</p>
    <c:if test="${ not empty requestScope.orders}">
        <ul>
        <c:forEach var="order" items="${requestScope.orders}">

            <li>
                ${order}
            </li>
        </c:forEach>
        </ul>
    </c:if>
    <c:if test="${requestScope.orders.isEmpty()}">
       <div style="color:red;">
           <p>
               There is no any orders
           </p>
       </div>
    </c:if>


</form>

</body>
</html>
