<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Search Employee</title></head>
<body>
    <h2>SELECT Employee by Id</h2>
    
    <c:if test="${not empty requestScope.message}">
        <p style="color: red;">${requestScope.message}</p>
    </c:if>
    
    <form action="EmployeeCRUD_Servlet" method="post">
        <input type="hidden" name="action" value="searchAndDisplay">
        <label for="id">Employee ID:</label>
        <input type="number" id="id" name="id" required><br><br>
        <input type="submit" value="Search">
    </form>
    
    <p><a href="home_menu.jsp">Back to Menu</a></p>
</body>
</html>
