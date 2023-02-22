package com.system.bumblebee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    @GetMapping(value = "/")
    public String getHome() {
        return "Home";
    }
    @GetMapping(value = "/AdminLogin")
    public String getAdminLogin() {
        return "AdminLogin";
    }
    @GetMapping(value = "/AdminCenter")
    public String getAdminCenter() {
            return "AdminCenter";
        }
    @GetMapping(value = "/CustomerRegister")
    public String getCustomerRegister() {
        return "CustomerRegister";
    }
    @GetMapping(value = "/CustomerDetails")
    public String getCustomerDetails() {
        return "CustomerDetails";
    }
    @GetMapping(value = "/Registration-Success")
    public String getRegistrationSuccess() {
        return "CustomerRegSuccess";
    }
    @GetMapping(value = "/CustomerProfile")
    public String getCustomerProfile(){
        return "CustomerProfile";
    }
}
