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
        <th>Action</th>
    </tr>


    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="meal">
        <tr>
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>
                <a href="editmeal?id=${meal.id}">edit</a>
                <a href="editmeal">edit2</a>
                <a href="/delete/${meal.id}">delete</a>
            </td>
        </tr>
    </c:forEach>

</table>
<%--</c:if>--%>

<h2>Add</h2>
<c:url value="/add" var="add"/>
<a href="${add}">Add new meal</a>
</body>
</html>