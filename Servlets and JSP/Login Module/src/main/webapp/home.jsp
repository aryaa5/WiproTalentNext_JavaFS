<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
    <h2>Welcome!</h2>
    <p>You have successfully logged in.</p>
    
    <%-- Display the logged-in user (optional) --%>
    <% String user = (String) request.getSession().getAttribute("username"); %>
    <% if (user != null) { %>
        <p>Hello, **<%= user %>**!</p>
    <% } %>

    <p><a href="index.html">Logout / Back to Login</a></p>
</body>
</html>
