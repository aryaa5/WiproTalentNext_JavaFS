package com.example.login.servlet;

import com.example.login.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new UserDao(); // DAO instance

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (userDao.isValidUser(username, password)) {
            // Valid user: Redirect to home page
            request.getSession().setAttribute("username", username); // Set session for home page
            response.sendRedirect("home.jsp");
        } else {
            // Invalid credentials: Redisplay login form with error message
            String errorMessage = "Invalid username or password. Please try again.";
            request.setAttribute("message", errorMessage);
            
            // Forward back to index.html (or index.jsp) to display the message
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}
