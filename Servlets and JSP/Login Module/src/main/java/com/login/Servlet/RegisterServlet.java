package com.example.login.servlet;

import com.example.login.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");

        // Server-side validation (though client-side exists too)
        if (!password.equals(retypePassword)) {
            request.setAttribute("message", "Error: Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (userDao.registerUser(username, password)) {
            // Registration successful: Redirect to Login Page
            // Note: The prompt asks to redirect to the Login Page, not set a message there.
            response.sendRedirect("index.html"); 
            // Optional: If you want a success message on the login page:
            // request.getSession().setAttribute("loginMessage", "Registration successful! Please login.");
            // response.sendRedirect("index.html");
        } else {
            // Username already exists: Redisplay form with error message
            request.setAttribute("message", "Error: Username **" + username + "** already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
