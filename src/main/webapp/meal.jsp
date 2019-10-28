<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>${param.action == "add" ? "Add meal" : "Edit meal"}</h2>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<c:url value="meals" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${meal.id}">
    <label for="dateTime">Date</label>
    <input type="text" name="dateTime" id="dateTime" value="${meal.dateTime}">
    <label for="calories">Description</label>
    <input type="text" name="description" id="description" value="${meal.description}">
    <label for="calories">Calory</label>
    <input type="text" name="calories" id="calories" value="${meal.calories}">
    <button type="submit">Save</button>
    <button type="reset" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>