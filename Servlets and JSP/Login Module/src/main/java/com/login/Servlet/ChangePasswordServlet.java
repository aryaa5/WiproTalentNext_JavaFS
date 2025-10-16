package com.example.login.servlet;

import com.example.login.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String retypeNewPassword = request.getParameter("retypeNewPassword");
        
        String message;

        // Condition 1: New password and retype must be the same
        if (!newPassword.equals(retypeNewPassword)) {
            message = "Error: New password and Retype New Password do not match.";
        } 
        // Condition 2: Check if the username and old password are correct
        else if (!userDao.isValidUser(username, oldPassword)) {
            message = "Error: Invalid Username or Old Password.";
        }
        // All conditions met: Update the password
        else if (userDao.updatePassword(username, newPassword)) {
            message = "Success: Password for **" + username + "** has been updated.";
        } else {
            // Fallback for non-existent user (though covered by isValidUser, good for robustness)
            message = "Error: Could not update password. User may not exist.";
        }

        // Redisplay the change password form with the message
        request.setAttribute("message", message);
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }
}
