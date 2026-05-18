package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repo;
    @Autowired
    private JavaMailSender mailSender;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return repo.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    // ✅ KEEP ONLY ONE
    public long getTotalCustomers() {
        return repo.count();
    }

    public long getActiveCustomers() {
        return repo.countByStatus("ACTIVE");
    }

    public long getNewCustomersThisMonth() {
        return repo.countNewThisMonth();
    }
}