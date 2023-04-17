<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 14.08.2022
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="styles/test.css"%>
    </style>

</head>
<body>


<form action="${pageContext.request.contextPath}/authentication" method="post">

    <label> Email :
        <input type="text" name="email" value="${param.email}">
    </label><br>
    <label>Password :
        <input type="password" name="password">
    </label>
    <br>

    <label>Role :
        <select name="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">
                    ${role}
                </option>
            </c:forEach>
        </select>
    </label>
    <br>
    <button type="submit">logIn</button>

    <a href="${pageContext.request.contextPath}/registration"><button type="button">register</button></a>

    <c:if test="${param.error != null}">
        <div style="color: red;">
            email or password or role are incorrect
        </div>
    </c:if>
</form>

</body>
</html>
