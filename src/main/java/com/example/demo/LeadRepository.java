package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    long countByStatus(String status);
    @Query("SELECT COUNT(l) FROM Lead l")
    Long getTotalLeads();
    
   
    List<Lead> findByName(String name);
}