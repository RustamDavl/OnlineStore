<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 13.08.2022
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>

<form action="${pageContext.request.contextPath}/addExisting" method="post">

    <label> Select the product :
        <select name="productName">

            <c:forEach var="product" items="${requestScope.products}">
                <option value="${product}">

                        ${product}
                </option>
            </c:forEach>
        </select>
    </label>
    <label>Amount :
        <input type="text" name="amount">
    </label>
<br>
    <button type="submit">Add</button>

    <c:if test="${!requestScope.errors.isEmpty()}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">

                <p>
                        ${error}
                </p>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${!requestScope.update.isEmpty()}">
        <div style="color: green;">
            <c:forEach var="s" items="${requestScope.update}">
                <p>${s}</p>
            </c:forEach>
        </div>
    </c:if>
</form>


</body>
</html>
