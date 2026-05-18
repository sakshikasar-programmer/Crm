package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
@Controller
public class SaleController {

    @Autowired
    private SaleService service;
	private Object to;
	private Object salesRepo;

    // Show Sales Page
    @GetMapping("/sales")
    public String salesPage(Model model) {

        model.addAttribute("sales", service.getAllSales());
        model.addAttribute("totalSales", service.totalSales());
        model.addAttribute("orders", service.totalOrders());
        model.addAttribute("pending", service.pendingDeals());
        model.addAttribute("completed", service.completedDeals());

        return "sales";
    }

    // Open Add Form
    @GetMapping("/add-sale")
    public String addSaleForm(Model model) {
        model.addAttribute("sale", new sale());
        return "add-sale";
    }

    // Save Sale
    @PostMapping("/save-sale")
    public String saveSale(@ModelAttribute sale sale) {
        service.saveSale(sale);
        return "redirect:/sales";
    }

    // Delete Sale
    @GetMapping("/delete-sale/{id}")
    public String deleteSale(@PathVariable Long id) {
        service.deleteSale(id);
        return "redirect:/sales";
    }

    // Edit Sale
    @GetMapping("/edit-sale/{id}")
    public String editSale(@PathVariable Long id, Model model) {

        sale sale = service.getSaleById(id);

        if (sale == null) {
            return "redirect:/sales"; // avoid crash
        }

        model.addAttribute("sale", sale);
        return "add-sale";
    }
}