<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 16.08.2022
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:if test="${not empty sessionScope.buyer}">
        <form action="${pageContext.request.contextPath}/logout" method="post">


            <button type="submit">Logout</button>
        </form>
    </c:if>
    <c:if test="${not empty sessionScope.admin}">
        <form action="${pageContext.request.contextPath}/logout" method="post">


            <button type="submit">Logout</button>
        </form>
    </c:if>


</div>
