package com.xyzlibrary.servlet;

import com.xyzlibrary.dao.UserProfileDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserProfileDAO dao = new UserProfileDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (dao.validateUser(email, password)) {
            // Valid credentials: Go to home page (Feature c)
            request.getSession().setAttribute("userEmail", email);
            response.sendRedirect("home.jsp");
        } else {
            // Invalid credentials: Display error on top of login page (Feature c)
            request.setAttribute("loginError", "Invalid Credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
