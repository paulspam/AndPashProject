<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:url value="/editmeal" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${meal.id}">
    <label for="dateTime">Date</label>
    <input type="text" name="dateTime" id="dateTime" value="${meal.dateTime}">
    <label for="calories">Description</label>
    <input type="text" name="description" id="description" value="${meal.description}">
    <label for="calories">Calory</label>
    <input type="text" name="calories" id="calories" value="${meal.calories}">
    <input type="submit" value="Edit film" value="${meal.calory}">
</form>
</body>
</html>