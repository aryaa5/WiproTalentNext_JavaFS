package com.xyzlibrary.servlet;

import com.xyzlibrary.dao.UserProfileDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserProfileDAO dao = new UserProfileDAO();
    
    // Simple Email Regex (covers most common cases)
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        if (email == null || email.trim().isEmpty()) {
            out.print(""); // Do nothing if field is empty
            return;
        }

        // 1. Check for valid email pattern (Feature b)
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            out.print("Invalid email-id"); // Response for client-side
            return;
        }

        // 2. Check for existence (Feature a)
        if (dao.emailExists(email)) {
            out.print("Email already exists!"); // Response for client-side
        } else {
            out.print("Valid email address"); // Success message (optional, but good feedback)
        }
    }
}
