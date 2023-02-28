// Define the package and import necessary classes
package com.system.bumblebee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Define the Controller
@Controller
public class UIController {
    // Define a method to handle requests for the home page
    @GetMapping(value = "/")
    public String getHome() {
        return "Home";
    }
    // Define a method to handle requests for the admin login page
    @GetMapping(value = "/admin_login")
    public String getAdminLogin() {
        return "AdminLogin";
    }
    // Define a method to handle requests for the admin center page
    @GetMapping(value = "/admin_center")
    public String getAdminCenter() {
        return "AdminCenter";
    }
    // Define a method to handle requests for the customer registration page
    @GetMapping(value = "/register")
    public String getCustomerRegister() {
        return "Register";
    }
    // Define a method to handle requests for the customer details page
    @GetMapping(value = "/customer_details")
    public String getCustomerDetails() {
        return "CustomerDetails";
    }
    // Define a method to handle requests for the customer registration success page
    @GetMapping(value = "/registration_success")
    public String getRegistrationSuccess() {
        return "CustomerRegSuccess";
    }
    // Define a method to handle requests for the customer profile page
    @GetMapping(value = "/customer_profile")
    public String getCustomerProfile(@RequestParam Long cusID, Model model){
        model.addAttribute("cusID", cusID); // add the cusID parameter to the model
        return "CustomerProfile";
    }
    // Define a method to handle requests for the inventory details page
    @GetMapping(value = "/inventory_details")
    public String getInventoryDetails() {
        return "InventoryDetails";
    }
    // Define a method to handle requests for the inventory type page
    @GetMapping(value = "/inventory_type")
    public String getInventoryType(@RequestParam String type, Model model) {
        model.addAttribute("type", type); // add the type parameter to the model
        return "InventoryType";
    }
}
