package com.app.controller;

import com.app.dao.UserDao;
import com.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("currentUser") // Used for security (Reason 4)
public class UserController {

    private final UserDao userDao = new UserDao(); // In real app, this would be injected

    // 1. Home Page (Index/Menu)
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showHome(Model model) {
        return "home"; // home.jsp
    }

    // --- Registration Logic ---

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration"; // registration.jsp
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result, Model model) {
        
        // Check Model validation errors first (1, 2, 3, 4)
        if (result.hasErrors()) {
            return "registration";
        }

        // Check for duplicate username
        if (userDao.userExists(user.getUsername())) {
            // Add custom error for duplicate
            model.addAttribute("regError", "Username already taken. Please choose another.");
            return "registration";
        }

        userDao.saveUser(user);
        model.addAttribute("loginMessage", "Registration successful! Please login.");
        return "redirect:/login"; // Redirect to login page
    }

    // --- Login Logic ---

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // login.jsp
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("user") User user, HttpSession session, Model model) {

        String username = user.getUsername();
        String password = user.getPassword();

        // 1. Check if username/password are empty
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("loginError", "1. There was no username/password provided.");
            return "login";
        }
        
        // 2. Check if user exists (for better error message)
        if (!userDao.userExists(username)) {
            model.addAttribute("loginError", "2. There is no such user in the system.");
            return "login";
        }
        
        // 3. Check combination
        if (userDao.validateUser(username, password)) {
            // Login successful: Store username in session
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", username);
            return "redirect:/welcome";
        } else {
            // Login failed
            model.addAttribute("loginError", "3. The combination of username/password is wrong.");
            return "login";
        }
    }

    // --- Welcome/Secured Page Logic ---

    // 4. Secured Page Access (Welcome Page)
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String showWelcomePage(HttpSession session, Model model) {
        
        // Security check: Cannot be accessed directly without login
        if (session.getAttribute("isLoggedIn") == null || !(boolean)session.getAttribute("isLoggedIn")) {
            model.addAttribute("loginError", "You must log in to access the Welcome Page.");
            return "redirect:/login"; // Redirect to login
        }
        
        String username = (String) session.getAttribute("username");
        model.addAttribute("welcomeMessage", "Welcome " + username + " - if " + username + " is the user name.");
        return "welcome"; // welcome.jsp
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
