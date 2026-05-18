package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReportController {

    @Autowired
    private SaleRepository salesRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private LeadRepository leadRepo;

    // 🔹 Load reports page
    @GetMapping("/reports")
    public String showReports(Model model) {

        Double revenue = salesRepo.getTotalRevenue();
        if (revenue == null) revenue = 0.0;

        Long customers = customerRepo.getTotalCustomers();
        Long leads = leadRepo.getTotalLeads();

        double conversionRate = 0;
        if (leads != null && leads != 0) {
            conversionRate = (customers * 100.0) / leads;
        }

        model.addAttribute("revenue", revenue);
        model.addAttribute("customers", customers);
        model.addAttribute("leads", leads);
        model.addAttribute("rate", conversionRate);

        model.addAttribute("reports", new ArrayList<>());

        return "reports";
    }

}