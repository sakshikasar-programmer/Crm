package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerLoginController {

    @Autowired
    private CustomerRepository customerRepo;
  
    @Autowired
    private SaleRepository salerepo;
    @Autowired
   
    private LeadRepository leadrepo;

    @GetMapping("/customer_login")
    public String customerLoginPage() {

        return "customer-login";
    }

    @PostMapping("/customer-login")
    public String customerLogin(@RequestParam String email,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

    	Customer customer =
                customerRepo.findByEmailAndPassword(email, password);


        if(customer != null) {

            session.setAttribute("customer",
                    customer);

            // Sales Data
            model.addAttribute("sales",
                    salerepo.findByCustomer(
                            customer.getName()));

            // Leads Data
            model.addAttribute("leads",
                    leadrepo.findByName(
                            customer.getName()));

            return "customer_dashboard";

        } else {

            model.addAttribute("error",
                    "Invalid Email or Password");

            return "customer-login";
        }
    }
}