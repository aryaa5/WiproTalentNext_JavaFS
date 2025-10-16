package com.legionbank.cashback.controller;

import com.legionbank.cashback.model.Customer;
import com.legionbank.cashback.service.CashBackService;
import com.legionbank.cashback.service.ClaimResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class CashBackController {

    @Autowired
    private CashBackService cashBackService;

    // --- GET: Login Screen ---
    @GetMapping("/")
    public String showLogin(HttpSession session) {
        // Invalidate session on access to root for a clean start/logout
        session.invalidate();
        return "login"; 
    }

    // --- POST: Handle Login ---
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String customerId, 
            @RequestParam String password, 
            Model model, 
            HttpSession session) {
        
        try {
            Optional<Customer> customerOpt = cashBackService.login(customerId, password);
            
            if (customerOpt.isEmpty()) {
                // Invalid ID or Password (Service returns empty Optional)
                model.addAttribute("error", "Invalid Customer ID or Password.");
                return "login";
            }
            
            Customer customer = customerOpt.get();
            session.setAttribute("customerId", customer.getCustomerId());
            model.addAttribute("balance", customer.getBalance());
            
            return "redirect:/claim"; // Redirect to prevent form resubmission
            
        } catch (IllegalArgumentException e) {
            // Mandatory Field Validation (Service throws exception)
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // --- GET: Claim Screen (Requires Session) ---
    @GetMapping("/claim")
    public String showClaim(HttpSession session, Model model) {
        String customerId = (String) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/"; // Not logged in
        }
        
        // Fetch current balance for display (in case of error or first load)
        Customer customer = cashBackService.getCustomerRepository().findById(customerId)
                            .orElseThrow(() -> new IllegalStateException("Customer session data is invalid."));
                            
        model.addAttribute("balance", customer.getBalance());
        model.addAttribute("customerName", customerId);
        
        return "claim";
    }

    // --- POST: Handle Claim Offer ---
    @PostMapping("/claim-offer")
    public String handleClaim(
            @RequestParam String couponCode, 
            Model model, 
            HttpSession session) {
        
        String customerId = (String) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/";
        }

        try {
            // Process the claim (validation, calculation, DB update)
            ClaimResult result = cashBackService.claimCashBack(customerId, couponCode);
            
            model.addAttribute("percentage", result.offerPercentage());
            model.addAttribute("newBalance", result.newBalance());
            model.addAttribute("cashBackAmount", result.cashBackAmount());
            
            return "success";
            
        } catch (IllegalArgumentException e) {
            // Validation 2, 5: Invalid or empty coupon (Service throws exception)
            model.addAttribute("error", e.getMessage());
            
            // Re-render the claim page with error message
            return showClaim(session, model);
        }
    }
}
