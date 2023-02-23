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
    @GetMapping(value = "/AdminLogin")
    public String getAdminLogin(HttpSession session) {
        String sessionEmail = (String) session.getAttribute("email");

//        if (sessionEmail == null) {
            return "AdminLogin";
//        } else {
//            return "redirect:/AdminCenter";
//        }
    }
    @GetMapping(value = "/AdminCenter")
    public String getAdminCenter(HttpSession session) {
        String sessionEmail = (String) session.getAttribute("email");

//        if (sessionEmail == null) {
//            return "redirect:/AdminLogin";
//        } else {
            return "AdminCenter";
//        }
    }
    @GetMapping(value = "/CustomerRegister")
    public String getCustomerRegister() {
        return "CustomerRegister";
    }
    @GetMapping(value = "/CustomerDetails")
    public String getCustomerDetails(HttpSession session) {
        String sessionEmail = (String) session.getAttribute("email");

//        if (sessionEmail == null) {
//            return "redirect:/AdminLogin";
//        } else {
            return "CustomerDetails";
//        }
    }
    @GetMapping(value = "/Registration-Success")
    public String getRegistrationSuccess() {
        return "CustomerRegSuccess";
    }
    @GetMapping(value = "/CustomerProfile")
    public String getCustomerProfile(@RequestParam Long cusID, Model model){
        model.addAttribute("cusID", cusID);
        return "CustomerProfile";
    }
}
