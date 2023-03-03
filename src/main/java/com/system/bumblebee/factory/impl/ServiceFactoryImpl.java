package com.system.bumblebee.factory.impl;

import com.system.bumblebee.factory.ServiceFactory;
import com.system.bumblebee.services.*;
import com.system.bumblebee.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory {

    @Autowired
    private AuthServiceImpl authServiceImpl;
    @Autowired
    private RegistrationServiceImpl registrationService;
    @Autowired
    private BrandServiceImpl brandService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private ProductServiceImpl productService;

    @Override
    public AuthServiceImpl authService() {
        return authServiceImpl;
    }

    @Override
    public BrandService brandService() {
        return brandService;
    }

    @Override
    public CategoryService categoryService() {
        return categoryService;
    }

    @Override
    public CustomerService customerService() {
        return customerService;
    }

    @Override
    public ProductService productService() {
        return productService;
    }

    @Override
    public RegistrationService registrationService() {
        return registrationService;
    }
}
