package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repo;

    public List<sale> getAllSales() {
        return repo.findAll();
    }

    public double totalSales() {
        return repo.findAll()
                   .stream()
                   .mapToDouble(sale::getAmount)
                   .sum();
    }

    public long totalOrders() {
        return repo.count();
    }

    public long pendingDeals() {
        return repo.countByStatus("PENDING");
    }

    public long completedDeals() {
        return repo.countByStatus("COMPLETED");
    }

    public void saveSale(sale sale) {
        repo.save(sale);
    }

    public void deleteSale(Long id) {
        repo.deleteById(id);
    }

    public sale getSaleById(Long id) {
        return repo.findById(id).orElse(null);
    }
}