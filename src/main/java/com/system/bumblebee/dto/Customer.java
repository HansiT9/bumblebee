package com.system.bumblebee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String customerFullName;
    private String Dob;
    private String customerEmail;
    private String password;
    private String installmentPlan;
}
