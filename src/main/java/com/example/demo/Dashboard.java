package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {
 
	@Autowired
	    private CustomerRepository customerRepo;

	    @Autowired
	    private LeadRepository leadRepo;

	    @Autowired
	    private SaleRepository saleRepo;
	
    @GetMapping("/dashboard")
    
    public String dashboard(Model model) {

        // ✅ Fetch counts from DB
        Long totalCustomers = customerRepo.count();
        Long totalLeads = leadRepo.count();
        Long totalSales = saleRepo.count();
        Long totalTasks = saleRepo.countTasks(); // ✅ correct
   //     Long totalTasks = taskRepo.count();

        // ✅ Send to UI
        model.addAttribute("customers", totalCustomers);
        model.addAttribute("leads", totalLeads);
        model.addAttribute("sales", totalSales);
     model.addAttribute("tasks", totalTasks);

        return "dashboard";
    }


   // @GetMapping("/sales")
   // public String sales() {
    //    return "sales"; // sales.html
    //}

   // @GetMapping("/customers")
   // public String customers() {
   //     return "customers";
   // }

  //  @GetMapping("/leads")
//    public String leads() {
 //       return "leads";
 //   }

  //  @GetMapping("/reports")
    //public String reports() {
      //  return "reports";
    //}
    
   

   
}