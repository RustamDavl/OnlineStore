<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 05.08.2022
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Registration page</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="nameId">Name :
        <input type="text" name="name" id="nameId">
    </label><br>
    <label for="lastNameId">Last name :
        <input type="text" name="lastName" id="lastNameId">
    </label><br>
    <label for="birthdayId">Birthday :
        <input type="date" name="birthday" id="birthdayId">
    </label><br>


    <label for="emailId">Email :
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="passwordId">Password :
        <input type="password" name="password" id="passwordId">
    </label><br>
    <label for="phoneId">Phone :
        <input type="tel" name="phone" id="phoneId">
    </label><br>


    <label>Role :
        <select name="role">
            <c:forEach var="role" items="${requestScope.roles}">


            <option value="${role}">
                ${role}
            </option>
            </c:forEach>
        </select>
    </label>

    <h4>Fill in your address :</h4>
    <label for="cityId">City :
        <input type="text" name="city" id="cityId">
    </label><br>
    <label for="streetId">Street :
        <input type="text" name="street" id="streetId">
    </label><br>
    <label for="numId">House number :
        <input type="text" name="houseNum" id="numId">
    </label><br>
    <label for="flatId">Flat number :
        <input type="text" name="flatNum" id="flatId">
    </label><br>

    <button type="submit">register</button>

    <c:if test="${!requestScope.errors.isEmpty()}">
        <div style="color: red">


            <c:forEach var="error" items="${requestScope.errors}">
                <p>${error}</p>
            </c:forEach>

        </div>
    </c:if>




</form>

</body>
</html>
