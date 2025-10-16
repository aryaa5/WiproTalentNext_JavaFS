<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<body>
    <h2>Change Password</h2>

    <%-- Display message from ChangePasswordServlet --%>
    <% 
        String message = (String) request.getAttribute("message");
        if (message != null) { 
    %>
        <p style="color: blue;"><%= message %></p>
    <% 
        } 
    %>

    <form action="ChangePasswordServlet" method="post" onsubmit="return validateChangePasswordForm()">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        
        <label for="oldPassword">Old Password:</label>
        <input type="password" id="oldPassword" name="oldPassword" required><br><br>

        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>

        <label for="retypeNewPassword">Retype New Password:</label>
        <input type="password" id="retypeNewPassword" name="retypeNewPassword" required><br><br>

        <input type="submit" value="Change Password">
    </form>

    <p><a href="index.html">Back to Login</a></p>

    <script>
        // Client-side validation
        function validateChangePasswordForm() {
            const newPassword = document.getElementById('newPassword').value;
            const retypeNewPassword = document.getElementById('retypeNewPassword').value;
            
            if (newPassword !== retypeNewPassword) {
                alert("Error: New Password and Retype New Password do not match.");
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
