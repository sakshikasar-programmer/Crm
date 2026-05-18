package com.example.demo;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // ✅ Load customers page
    @GetMapping("/customers")
    public String getCustomers(Model model) {

        model.addAttribute("customers", service.getAllCustomers());

        model.addAttribute("totalCustomers", service.getTotalCustomers());
        model.addAttribute("activeCustomers", service.getActiveCustomers());
        model.addAttribute("newCustomers", service.getNewCustomersThisMonth());

        return "customers";
    }

    // ✅ Open add form
    @GetMapping("/add-customer")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/save-customer")
    public String saveCustomer(@ModelAttribute Customer customer,
                               RedirectAttributes redirectAttributes) {

        // NEW CUSTOMER

        if (customer.getId() == null) {

            customer.setCreatedDate(LocalDate.now());

            customer.setStatus("ACTIVE");

            // GENERATE PASSWORD

            String generatedPassword =
                    "CRM" + new Random().nextInt(9999);

            customer.setPassword(generatedPassword);

            // SAVE CUSTOMER

            service.saveCustomer(customer);

            try {

                // SEND EMAIL

                SimpleMailMessage msg =
                        new SimpleMailMessage();

                msg.setTo(customer.getEmail());

                msg.setSubject("CRM Login Details");

                msg.setText(

                        "Welcome To CRM System\n\n" +

                        "Your Login Details:\n\n" +

                        "Email : " + customer.getEmail() + "\n" +

                        "Password : " + generatedPassword + "\n\n" +

                        "Thank You"

                );

                mailSender.send(msg);

                redirectAttributes.addFlashAttribute(
                        "success",
                        "Customer Added & Email Sent Successfully!"
                );

            }

            catch (Exception e) {

                redirectAttributes.addFlashAttribute(
                        "error",
                        "Customer Saved But Email Failed!"
                );
            }
        }

        // EDIT CUSTOMER

        else {

            Customer existing =
                    service.getCustomerById(customer.getId());

            customer.setCreatedDate(
                    existing.getCreatedDate());

            customer.setStatus(
                    existing.getStatus());

            customer.setPassword(
                    existing.getPassword());

            service.saveCustomer(customer);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Customer Updated Successfully!"
            );
        }

        // REDIRECT TO DASHBOARD

        return "redirect:/dashboard";
    }
    // ✅ Delete
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        service.deleteCustomer(id);
        return "redirect:/customers";
    }

    // ✅ Edit
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getCustomerById(id));
        return "add-customer";
    }
}