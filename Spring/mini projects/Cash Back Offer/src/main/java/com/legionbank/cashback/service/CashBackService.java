package com.legionbank.cashback.service;

import com.legionbank.cashback.model.Customer;
import com.legionbank.cashback.model.Coupon;
import com.legionbank.cashback.repository.CustomerRepository;
import com.legionbank.cashback.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

// Helper class for claim result
public record ClaimResult(int offerPercentage, BigDecimal newBalance, BigDecimal cashBackAmount) {}

@Service
public class CashBackService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CouponRepository couponRepository;

    // Expose repository for controller to fetch balance refresh
    public CustomerRepository getCustomerRepository() { return customerRepository; }

    // --- LOGIN VALIDATION (Requirements 2, 3, 4) ---
    public Optional<Customer> login(String customerId, String password) {
        // Validation 2: Mandatory fields
        if (customerId == null || customerId.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID and Password are mandatory.");
        }

        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        // Validation 3: Invalid Customer ID
        if (customerOpt.isEmpty()) {
            return Optional.empty(); 
        }

        Customer customer = customerOpt.get();
        // Validation 4: Wrong Password
        if (!customer.getPassword().equals(password)) {
            return Optional.empty(); 
        }

        return customerOpt;
    }

    // --- CASH BACK CLAIM LOGIC (Requirements 2, 5, Calculation) ---
    @Transactional
    public ClaimResult claimCashBack(String customerId, String couponCode) {
        
        // Validation 2: Coupon Code is mandatory
        if (couponCode == null || couponCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Coupon Code is mandatory.");
        }

        // Fetch Customer
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Logged-in Customer not found."));

        // Validation 5: Invalid coupon code
        Optional<Coupon> couponOpt = couponRepository.findById(couponCode);
        if (couponOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid Coupon Code entered.");
        }
        Coupon coupon = couponOpt.get();

        // Calculation: Cash Back = Balance * (Percentage / 100)
        BigDecimal currentBalance = customer.getBalance();
        BigDecimal percentage = BigDecimal.valueOf(coupon.getOfferPercentage());
        
        // Scale 2 for currency, RoundingMode.HALF_UP for standard banking rounding
        BigDecimal cashBackAmount = currentBalance
            .multiply(percentage)
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        // Update Balance
        BigDecimal newBalance = currentBalance.add(cashBackAmount).setScale(2, RoundingMode.HALF_UP);
        customer.setBalance(newBalance);
        customerRepository.save(customer); // Hibernate/JPA saves the update

        // Return Result (Note: 0% cash back is handled correctly by the calculation)
        return new ClaimResult(
            coupon.getOfferPercentage(), 
            newBalance, 
            cashBackAmount
        );
    }
}
