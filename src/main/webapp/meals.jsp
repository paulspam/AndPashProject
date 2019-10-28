<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <th>Action</th>
    </tr>


    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="meal">


        <tr style="color:${meal.exceed ? 'red' : 'greenyellow' }">

            <td>
                <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="meals?action=edit&id=${meal.id}">edit</a>
                <a href="meals?action=delete&id=${meal.id}">delete</a>
            </td>
        </tr>
    </c:forEach>

</table>
<%--</c:if>--%>

<h2>Add</h2>
<%--<c:url value="/add" var="add"/>--%>
<a href="meals?action=add">Add new meal</a>
</body>
</html>