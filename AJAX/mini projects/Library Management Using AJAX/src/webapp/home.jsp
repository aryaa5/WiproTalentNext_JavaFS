<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Home</title>
</head>
<body>
    <% String userEmail = (String) session.getAttribute("userEmail"); %>

    <h2>Welcome to the XYZ Library!</h2>
    
    <% if (userEmail != null) { %>
        <p>You are logged in as: <strong><%= userEmail %></strong></p>
        <p>You now have access to the books.</p>
    <% } else { %>
        <p>Please <a href="login.jsp">log in</a> to view the content.</p>
    <% } %>

    <%-- Placeholder for book management features --%>
    <p><a href="login.jsp">Logout</a></p>
</body>
</html>
