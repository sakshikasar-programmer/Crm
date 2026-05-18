package com.example.demo;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity(name = "sale")
@Table(name = "sales")
public class sale {   // ✅ Capital S

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer;
    private String product;
    private double amount;
    private String status;
    //private LocalDate saleDate;

    // Getters & Setters

    public Long getId() {
        return id;
    }
   
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
   

    
    

}