<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 08.08.2022
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="Logout.jsp"%>
<form action="${pageContext.request.contextPath}/addGoods" method="post" enctype="multipart/form-data">

    <label for="productId">Product name :
        <input type="text" name="productName" id="productId">
    </label><br>
    <label for="descriptionId">Product description :</label><br>
    <textarea rows="8" cols="30" type="text" name="productDescription" id="descriptionId"></textarea><br>
    <label for="priceId">Product price :
        <input type="text" name="productPrice" id="priceId">
    </label><br>
    <label for="quantityId">Product quantity :
        <input type="text" name="productQuantity" id="quantityId">
    </label><br>
    <label for="imageId">Product image :
        <input type="file" name="productImage" id="imageId">
    </label><br>

    <button type="submit">SAVE</button><br>
    <a href="${pageContext.request.contextPath}/adminHomepage"><button type="button">main page</button></a>

    <c:if test="${!requestScope.errors.isEmpty()}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">

                <p>
                        ${error}
                </p>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${!requestScope.saved.isEmpty()}">
        <div style="color: green;">
            <c:forEach var="s" items="${requestScope.saved}">
                <p>${s}</p>
            </c:forEach>
        </div>
    </c:if>




</form>
</body>
</html>
