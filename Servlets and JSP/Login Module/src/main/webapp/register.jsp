<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register New User</title>
</head>
<body>
    <h2>Register New User</h2>

    <%-- Display message from RegisterServlet --%>
    <% 
        String message = (String) request.getAttribute("message");
        if (message != null) { 
    %>
        <p style="color: blue;"><%= message %></p>
    <% 
        } 
    %>
    
    <form action="RegisterServlet" method="post" onsubmit="return validateRegistrationForm()">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="retypePassword">Retype Password:</label>
        <input type="password" id="retypePassword" name="retypePassword" required><br><br>

        <input type="submit" value="Add">
    </form>

    <p><a href="index.html">Back to Login</a></p>

    <script>
        // Client-side validation
        function validateRegistrationForm() {
            const password = document.getElementById('password').value;
            const retypePassword = document.getElementById('retypePassword').value;
            
            if (password !== retypePassword) {
                alert("Error: Password and Retype Password do not match.");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
