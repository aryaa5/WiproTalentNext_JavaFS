<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <style>
        .message { color: blue; font-weight: bold; }
        .error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>XYZ Library - User Login</h2>

    <%-- Display success message from successful registration (Feature c) --%>
    <% String regSuccess = (String) session.getAttribute("regSuccess"); %>
    <% if (regSuccess != null) { %>
        <p class="message"><%= regSuccess %></p>
        <% session.removeAttribute("regSuccess"); // Clear message after display %>
    <% } %>
    
    <%-- Display error message for invalid credentials (Feature c) --%>
    <% String loginError = (String) request.getAttribute("loginError"); %>
    <% if (loginError != null) { %>
        <p class="error"><%= loginError %></p>
    <% } %>

    <form action="LoginServlet" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="Login">
    </form>
    
    <p>New user? <a href="register.jsp">Register now</a></p>
</body>
</html>
