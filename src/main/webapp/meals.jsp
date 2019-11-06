<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meals</title>
    <link rel="stylesheet" href="resources/css/w3.css">
</head>
<body class="w3-light-grey">
<h3><a href="index.html">Home</a></h3>
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
<h2>Meals</h2>
</div>
<table class="w3-table-all">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calory</th>
        <th>Action</th>
    </tr>
    <thead>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="meal">
        <tr style="color:${meal.exceed ? 'purple' : 'teal' }">
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
<h3>Add</h3>
<a href="meals?action=add">Add new meal</a>
</body>
</html>