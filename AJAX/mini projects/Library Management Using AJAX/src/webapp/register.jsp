<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        .error { color: red; }
        .feedback { font-size: 0.9em; margin-left: 10px; }
    </style>
</head>
<body>
    <h2>XYZ Library - User Registration</h2>

    <%-- Display server-side registration failure (if any) --%>
    <% String regError = (String) request.getAttribute("regError"); %>
    <% if (regError != null) { %>
        <p class="error"><%= regError %></p>
    <% } %>

    <form action="RegisterServlet" method="post" id="registrationForm">
        
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" onkeyup="checkEmailStatus()" required>
        <span id="emailFeedback" class="feedback"></span><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <%-- Other fields from XYZ_PROFILE (Name, DOB, Gender, etc.) would go here --%>
        
        <input type="submit" value="Register" id="registerButton" disabled>
    </form>
    
    <p>Already registered? <a href="login.jsp">Login here</a></p>

    <script>
        // Function to validate and check email existence using AJAX
        function checkEmailStatus() {
            const emailInput = document.getElementById('email');
            const feedbackSpan = document.getElementById('emailFeedback');
            const registerButton = document.getElementById('registerButton');
            const email = emailInput.value;

            // Clear previous feedback and disable button immediately
            feedbackSpan.innerHTML = '';
            registerButton.disabled = true;

            if (email.trim() === '') {
                return; // Do nothing if empty
            }

            // Create XMLHttpRequest object
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const responseText = xhr.responseText.trim();
                    
                    if (responseText === "Invalid email-id") {
                        // Feature b: Invalid pattern
                        feedbackSpan.innerHTML = '<span class="error">' + responseText + '</span>';
                        registerButton.disabled = true;
                    } 
                    else if (responseText === "Email already exists!") {
                        // Feature a: Email exists
                        feedbackSpan.innerHTML = '<span class="error">' + responseText + '</span>';
                        registerButton.disabled = true;
                    } 
                    else {
                        // Valid and unique email
                        feedbackSpan.innerHTML = '<span style="color: green;">' + responseText + '</span>';
                        registerButton.disabled = false;
                    }
                }
            };

            // Send request to the servlet
            xhr.open("GET", "CheckEmailServlet?email=" + encodeURIComponent(email), true);
            xhr.send();
        }
    </script>
</body>
</html>
