<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.08.2022
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>

<form action="${pageContext.request.contextPath}/showOrderForAdmin" method="post">

    Show list of orders for this product :
    <label>
        <select name="prodName">
            <c:forEach var="product" items="${requestScope.products}">
                <option value="${product}">

                    ${product}

                </option>
            </c:forEach>

        </select>
    </label>
    <button type="submit">show</button>

</form>

</body>
</html>
