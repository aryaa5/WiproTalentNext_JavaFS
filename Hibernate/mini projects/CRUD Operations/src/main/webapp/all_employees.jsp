<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>All Employees</title></head>
<body>
    <h2>All Employee Details</h2>
    
    <c:if test="${not empty requestScope.message}">
        <p style="color: red;">${requestScope.message}</p>
    </c:if>

    <c:choose>
        <c:when test="${not empty requestScope.employeeList}">
            <table border="1" cellpadding="5">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Designation</th>
                    <th>Salary</th>
                    <th>City</th>
                    <th>Email ID</th>
                    <th>Mobile No</th>
                </tr>
                <c:forEach var="emp" items="${requestScope.employeeList}">
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.name}</td>
                        <td>${emp.gender}</td>
                        <td>${emp.designation}</td>
                        <td>${emp.salary}</td>
                        <td>${emp.city}</td>
                        <td>${emp.emailId}</td>
                        <td>${emp.mobileNo}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No employees found.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="home_menu.jsp">Back to Menu</a></p>
</body>
</html>
