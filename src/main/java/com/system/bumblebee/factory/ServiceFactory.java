package com.system.bumblebee.factory;

import com.system.bumblebee.services.*;

public interface ServiceFactory {
    AuthService authService();
    BrandService brandService();
    CategoryService categoryService();
    CustomerService customerService();
    ProductService productService();
    RegistrationService registrationService();

}
