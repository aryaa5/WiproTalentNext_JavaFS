package com.apauctions.controller;

import com.apauctions.model.BidData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BidController")
public class BidControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Collect and Bind Parameters to JavaBean
        BidData bidData = new BidData();
        
        // Form fields are all Strings from request.getParameter()
        String itemIdStr = request.getParameter("itemId");
        String itemNameStr = request.getParameter("itemName");
        String yourNameStr = request.getParameter("yourName");
        String emailAddressStr = request.getParameter("emailAddress");
        String amountBidStr = request.getParameter("amountBid");
        // Checkbox only sends a value if checked. If null, it's false.
        boolean autoIncrement = "true".equals(request.getParameter("autoIncrement")); 
        
        // 2. Server-Side Validation
        // A simple check for null or empty strings.
        boolean isValid = true;
        if (itemIdStr == null || itemIdStr.trim().isEmpty() ||
            itemNameStr == null || itemNameStr.trim().isEmpty() ||
            yourNameStr == null || yourNameStr.trim().isEmpty() ||
            emailAddressStr == null || emailAddressStr.trim().isEmpty() ||
            amountBidStr == null || amountBidStr.trim().isEmpty()) {
            
            isValid = false;
        }
        
        // Attempt to parse amountBid if validation passes initially
        double bidAmount = 0.0;
        if (isValid) {
            try {
                bidAmount = Double.parseDouble(amountBidStr);
            } catch (NumberFormatException e) {
                // Handle case where bid amount is not a valid number
                isValid = false;
            }
        }
        
        // 3. Process Request based on Validation Result
        if (isValid) {
            // Populate the JavaBean
            bidData.setItemId(itemIdStr.trim());
            bidData.setItemName(itemNameStr.trim());
            bidData.setYourName(yourNameStr.trim());
            bidData.setEmailAddress(emailAddressStr.trim());
            bidData.setAmountBid(bidAmount);
            bidData.setAutoIncrement(autoIncrement);

            // Set the bean as a request attribute
            request.setAttribute("bidData", bidData);

            // Forward to the success JSP
            request.getRequestDispatcher("/bidSubmitted.jsp").forward(request, response);

        } else {
            // Data is missing or invalid.
            
            // Re-populate the bean with existing (potentially partial) data to display it on the form
            // Also handle NumberFormatException by setting amount to 0 or keeping it as null/empty if desired
            bidData.setItemId(itemIdStr != null ? itemIdStr.trim() : "");
            bidData.setItemName(itemNameStr != null ? itemNameStr.trim() : "");
            bidData.setYourName(yourNameStr != null ? yourNameStr.trim() : "");
            bidData.setEmailAddress(emailAddressStr != null ? emailAddressStr.trim() : "");
            // amountBid might fail parsing, so we re-send the original string to the form
            try {
                bidData.setAmountBid(Double.parseDouble(amountBidStr));
            } catch (Exception e) {
                // If it fails, set a placeholder or use a safer mechanism if not using EL for redisplay
                bidData.setAmountBid(0.0); 
            }
            bidData.setAutoIncrement(autoIncrement);
            
            // Set error message and the partial data back to the request
            request.setAttribute("errorMessage", "Required Data Missing!");
            request.setAttribute("bidData", bidData); // Send data back for repopulation
            
            // Forward back to the form page (assuming form is bidForm.jsp)
            request.getRequestDispatcher("/bidForm.jsp").forward(request, response);
        }
    }
}
