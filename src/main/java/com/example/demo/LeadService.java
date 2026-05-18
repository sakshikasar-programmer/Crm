package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

    @Autowired
    private LeadRepository repo;

    public List<Lead> getAllLeads() {
        return repo.findAll();
    }

    public void saveLead(Lead lead) {
        repo.save(lead);
    }

    public long totalLeads() {
        return repo.count();
    }

    public long newLeads() {
        return repo.countByStatus("NEW");
    }

    public long convertedLeads() {
        return repo.countByStatus("CONVERTED");
    }

    public long contactedLeads() {
        return repo.countByStatus("CONTACTED");
    }

    public void deleteLead(Long id) {
        repo.deleteById(id);
    }
    public Lead getLeadById(Long id) {
        return repo.findById(id).orElse(null);
    }
}