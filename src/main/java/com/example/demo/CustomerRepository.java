package com.example.demo;

//package com.example.crm.repository;

import com.example.demo.Customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    long count(); // total

    long countByStatus(String status);

    @Query(value = "SELECT COUNT(*) FROM customers WHERE DATE_TRUNC('month', created_date) = DATE_TRUNC('month', CURRENT_DATE)", nativeQuery = true)
    long countNewThisMonth();
    
    @Query("SELECT COUNT(c) FROM Customer c")
    long getTotalCustomers();
    @Query("SELECT c FROM Customer c WHERE c.createdDate BETWEEN :from AND :to")
    List<Customer> findCustomersBetween(LocalDate from, LocalDate to);
    Customer findByEmail(String email);
    Customer findByEmailAndPassword(
            String email,
            String password
    );
    
}