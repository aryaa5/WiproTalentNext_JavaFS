package com.crud.servlet;

import com.crud.dao.EmployeeDAO;
import com.crud.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/PersonalModifyServlet")
public class PersonalModifyServlet extends HttpServlet {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Must be logged in
            return;
        }

        try {
            int userId = (int) session.getAttribute("userId");
            Employee employee = employeeDAO.getEmployeeById(userId);

            if (employee != null) {
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("modify_personal.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Error: User data not found.");
                request.getRequestDispatcher("home_menu.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error retrieving data: " + e.getMessage());
            request.getRequestDispatcher("home_menu.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int userId = (int) session.getAttribute("userId");

        try {
            // Retrieve only the modifiable fields
            String name = request.getParameter("name");
            String city = request.getParameter("city");
            String emailId = request.getParameter("emailId");
            String mobileNo = request.getParameter("mobileNo");
            
            // Get the full employee object to retain unmodifiable fields (like designation, salary)
            Employee existingEmp = employeeDAO.getEmployeeById(userId);
            
            if (existingEmp != null) {
                existingEmp.setName(name);
                existingEmp.setCity(city);
                existingEmp.setEmailId(emailId);
                existingEmp.setMobileNo(mobileNo);
                
                employeeDAO.updateEmployee(existingEmp);
                request.setAttribute("message", "Success: Personal details updated!");
            } else {
                request.setAttribute("message", "Error: Failed to find employee for update.");
            }
            
            // Re-fetch and display the updated employee data
            doGet(request, response); // Reuse doGet to display the form with success message
            
        } catch (Exception e) {
            request.setAttribute("message", "Update failed: " + e.getMessage());
            request.getRequestDispatcher("modify_personal.jsp").forward(request, response);
        }
    }
}
