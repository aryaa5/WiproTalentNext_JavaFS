package com.legionbank.cashback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Customer {
    @Id
    private String customerId;
    private String password;
    private BigDecimal balance;

    // Default constructor (required by JPA)
    public Customer() {}

    // Getters
    public String getCustomerId() { return customerId; }
    public String getPassword() { return password; }
    public BigDecimal getBalance() { return balance; }

    // Setters
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setPassword(String password) { this.password = password; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
