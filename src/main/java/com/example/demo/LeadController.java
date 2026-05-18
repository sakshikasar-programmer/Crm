package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeadController {

    @Autowired
    private LeadService service;

    @GetMapping("/leads")
    public String leadsPage(Model model) {
        model.addAttribute("leads", service.getAllLeads());
        model.addAttribute("total", service.totalLeads());
        model.addAttribute("newLeads", service.newLeads());
        model.addAttribute("converted", service.convertedLeads());
        model.addAttribute("pending", service.contactedLeads());

        return "leads";
    }

    // ➕ ADD FORM
    @GetMapping("/add-lead")
    public String addLeadForm(Model model) {
        model.addAttribute("lead", new Lead());
        return "add-lead";
    }

    // 💾 SAVE
    @PostMapping("/save-lead")
    public String saveLead(@ModelAttribute Lead lead) {
        service.saveLead(lead);
        return "redirect:/leads";
    }

    // ❌ DELETE
    @GetMapping("/delete-lead/{id}")
    public String deleteLead(@PathVariable Long id) {
        service.deleteLead(id);
        return "redirect:/leads";
    }
    
    @GetMapping("/edit-lead/{id}")
    public String editLead(@PathVariable Long id, Model model) {

        
		Lead lead = service.getLeadById(id);

        if (lead == null) {
            return "redirect:/leads";
        }

        model.addAttribute("lead", lead);
        return "add-lead";
    }
  
}