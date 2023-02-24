package com.system.bumblebee.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UIController {
    @GetMapping(value = "/")
    public String getHome() {
        return "Home";
    }
    @GetMapping(value = "/admin_login")
    public String getAdminLogin(HttpSession session) {
        String sessionEmail = (String) session.getAttribute("email");

//        if (sessionEmail == null) {
            return "AdminLogin";
//        } else {
//            return "redirect:/AdminCenter";
//        }
    }
    @GetMapping(value = "/admin_center")
    public String getAdminCenter(HttpSession session) {
        String sessionEmail = (String) session.getAttribute("email");

//        if (sessionEmail == null) {
//            return "redirect:/AdminLogin";
//        } else {
            return "AdminCenter";
//        }
    }
    @GetMapping(value = "/customer_register")
    public String getCustomerRegister() {
        return "CustomerRegister";
    }
    @GetMapping(value = "/customer_details")
    public String getCustomerDetails(HttpSession session) {
        String sessionEmail = (String) session.getAttribute("email");

//        if (sessionEmail == null) {
//            return "redirect:/AdminLogin";
//        } else {
            return "CustomerDetails";
//        }
    }
    @GetMapping(value = "/registration_success")
    public String getRegistrationSuccess() {
        return "CustomerRegSuccess";
    }
    @GetMapping(value = "/customer_profile")
    public String getCustomerProfile(@RequestParam Long cusID, Model model){
        model.addAttribute("cusID", cusID);
        return "CustomerProfile";
    }
    @GetMapping(value = "/inventory_details")
    public String getInventoryDetails() {
        return "InventoryDetails";
    }
    @GetMapping(value = "/inventory_type")
    public String getInventoryType(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        return "InventoryType";
    }
}
