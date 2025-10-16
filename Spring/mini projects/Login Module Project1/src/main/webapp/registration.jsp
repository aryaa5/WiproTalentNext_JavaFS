<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        .error { color: red; font-size: 0.9em; margin-left: 10px; display: block; }
        .global-error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Registration Page Details</h2>

    <c:if test="${not empty regError}">
        <p class="global-error">${regError}</p>
    </c:if>

    <form:form method="POST" action="register" modelAttribute="user">
        
        <label for="username">Username:</label>
        <form:input path="username" id="username" />
        <form:errors path="username" cssClass="error" /><br><br>

        <label for="password">Password (6 chars):</label>
        <form:password path="password" id="password" />
        <form:errors path="password" cssClass="error" /><br><br>
        
        <label for="employeeNumber">Employee No (A1234):</label>
        <form:input path="employeeNumber" id="employeeNumber" />
        <form:errors path="employeeNumber" cssClass="error" /><br><br>

        <label for="emailAddress">Email Address:</label>
        <form:input path="emailAddress" id="emailAddress" />
        <form:errors path="emailAddress" cssClass="error" /><br><br>
        
        <input type="submit" value="Register">
    </form:form>
    
    <p>Already registered? <a href="login">Login here</a></p>
    <p><a href="home">Back to Home</a></p>
</body>
</html>
