<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>


<%--<c:if test="${!empty itemList}">--%>
<table class="item-table">
    <tr>
        <th>Date</th>
        <th>Desscription</th>
        <th>Calory</th>
    </tr>


    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="meal">
        <tr>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>

</table>
<%--</c:if>--%>
</body>
</html>