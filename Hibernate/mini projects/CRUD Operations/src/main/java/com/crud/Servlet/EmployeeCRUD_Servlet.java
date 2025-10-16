package com.crud.servlet;

import com.crud.dao.EmployeeDAO;
import com.crud.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeCRUD_Servlet")
public class EmployeeCRUD_Servlet extends HttpServlet {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) action = "menu"; // Default to menu

        if (request.getSession().getAttribute("userId") == null) {
             response.sendRedirect("login.jsp");
             return;
        }
        
        try {
            switch (action) {
                case "addForm":
                    request.getRequestDispatcher("add_employee.jsp").forward(request, response);
                    break;
                case "deleteSearch":
                    request.getRequestDispatcher("delete_employee_search.jsp").forward(request, response);
                    break;
                case "selectSearch":
                    request.getRequestDispatcher("select_employee_search.jsp").forward(request, response);
                    break;
                case "selectAll":
                    listAllEmployees(request, response);
                    break;
                case "selectById":
                    selectEmployeeById(request, response);
                    break;
                case "deleteById":
                    deleteEmployeeById(request, response);
                    break;
                default:
                    request.getRequestDispatcher("home_menu.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            // Handle exceptions
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("home_menu.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (request.getSession().getAttribute("userId") == null) {
             response.sendRedirect("login.jsp");
             return;
        }

        switch (action) {
            case "add":
                addEmployee(request, response);
                break;
            case "searchAndDelete":
                searchAndDeleteForm(request, response);
                break;
            case "searchAndDisplay":
                selectEmployeeById(request, response);
                break;
            default:
                doGet(request, response);
        }
    }
    
    // --- Helper Methods for CRUD ---

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Retrieve all employee fields
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String designation = request.getParameter("designation");
            double salary = Double.parseDouble(request.getParameter("salary"));
            String city = request.getParameter("city");
            String emailId = request.getParameter("emailId");
            String mobileNo = request.getParameter("mobileNo");
            String password = request.getParameter("password"); // Default password for new user
            
            Employee newEmp = new Employee(name, gender, designation, salary, city, emailId, mobileNo, password);
            employeeDAO.addEmployee(newEmp);

            request.setAttribute("message", "Success: Employee added with ID: " + newEmp.getId());
        } catch (Exception e) {
            request.setAttribute("message", "Error adding employee: " + e.getMessage());
        }
        request.getRequestDispatcher("add_employee.jsp").forward(request, response);
    }

    // SELECT Employee by Id (used for both SELECT and DELETE search)
    private void selectEmployeeById(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("message", "Please enter a valid Employee ID.");
            request.getRequestDispatcher("select_employee_search.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(idParam);
        Employee employee = employeeDAO.getEmployeeById(id);
        
        if (employee != null) {
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("employee_details.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Error: Employee with ID " + id + " not found.");
            request.getRequestDispatcher("select_employee_search.jsp").forward(request, response);
        }
    }
    
    // DELETE Employee (Search part)
    private void searchAndDeleteForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // This is essentially the same logic as selectEmployeeById, but it forwards to a different page 
        // that displays a DELETE button.
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("message", "Please enter a valid Employee ID.");
            request.getRequestDispatcher("delete_employee_search.jsp").forward(request, response);
            return;
        }

        int id = Integer.parseInt(idParam);
        Employee employee = employeeDAO.getEmployeeById(id);
        
        if (employee != null) {
            request.setAttribute("employee", employee);
            // Forward to a page that confirms deletion
            request.getRequestDispatcher("employee_delete_confirm.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Error: Employee with ID " + id + " not found.");
            request.getRequestDispatcher("delete_employee_search.jsp").forward(request, response);
        }
    }

    // DELETE Employee (Final deletion action)
    private void deleteEmployeeById(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        employeeDAO.deleteEmployee(id);
        request.setAttribute("message", "Success: Employee with ID " + id + " has been deleted.");
        // Redirect back to the delete search form
        request.getRequestDispatcher("delete_employee_search.jsp").forward(request, response);
    }
    
    // SELECT ALL Employee
    private void listAllEmployees(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Employee> employees = employeeDAO.selectAllEmployees();
        request.setAttribute("employeeList", employees);
        request.getRequestDispatcher("all_employees.jsp").forward(request, response);
    }
}
