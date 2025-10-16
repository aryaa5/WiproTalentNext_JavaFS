<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Security Check: If the session attribute is missing, redirect.
    // This is a secondary layer, the controller's redirect is primary.
    if (session.getAttribute("isLoggedIn") == null || !(boolean)session.getAttribute("isLoggedIn")) {
        response.sendRedirect("login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Welcome</title></head>
<body>
    <h2>Welcome to the Application</h2>

    <h3>${welcomeMessage}</h3> 
    
    <p><a href="logout">Logout</a></p>
</body>
</html>
