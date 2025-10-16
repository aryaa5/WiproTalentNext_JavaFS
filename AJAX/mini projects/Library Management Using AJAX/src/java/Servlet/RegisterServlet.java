package com.xyzlibrary.servlet;

import com.xyzlibrary.dao.UserProfileDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserProfileDAO dao = new UserProfileDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve registration data (only Email and Password shown for core logic)
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // Other fields (Name, DOB, etc.) would be retrieved here

        if (dao.registerUser(email, password)) {
            // Successful registration: Redirect to Login Page (Feature c)
            request.getSession().setAttribute("regSuccess", "Registration successful! Please log in.");
            response.sendRedirect("login.jsp");
        } else {
            // Failed registration (Email already exists): Redirect back to registration page with error
            request.setAttribute("regError", "Registration failed: Email already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
