package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaleRepository extends JpaRepository<sale, Long> {

    long countByStatus(String status);

    @Query("SELECT SUM(s.amount) FROM sale s")
    Double getTotalRevenue();
    @Query("SELECT COUNT(c) FROM sale c WHERE c.status = 'PENDING'")
    Long countTasks();
    List<sale> findByCustomer(String customer);
    
}