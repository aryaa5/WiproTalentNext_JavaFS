<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="employee" value="${requestScope.employee}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Personal Details</title>
</head>
<body>
    <h2>Modify Personal Details</h2>
    
    <c:if test="${not empty requestScope.message}">
        <p style="color: blue;">${requestScope.message}</p>
    </c:if>

    <c:choose>
        <c:when test="${not empty employee}">
            <form action="PersonalModifyServlet" method="post">
                <input type="hidden" name="id" value="${employee.id}">
                
                <p>Employee ID: <b>${employee.id}</b> (Cannot be modified)</p>
                
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${employee.name}" required><br><br>

                <label for="emailId">Email ID:</label>
                <input type="email" id="emailId" name="emailId" value="${employee.emailId}" required><br><br>

                <label for="city">City:</label>
                <input type="text" id="city" name="city" value="${employee.city}" required><br><br>
                
                <label for="mobileNo">Mobile No:</label>
                <input type="text" id="mobileNo" name="mobileNo" value="${employee.mobileNo}" required><br><br>

                <p>Designation: ${employee.designation}</p>
                <p>Salary: ${employee.salary}</p>

                <input type="submit" value="Modify">
            </form>
        </c:when>
        <c:otherwise>
            <p style="color: red;">Error: Employee details could not be retrieved.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="home_menu.jsp">Back to Menu</a></p>
</body>
</html>
