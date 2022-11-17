package com.masai.controller;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdministratorServices {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addcategory")
    public Category addCategory(@RequestBody Category category) throws CategoryException {
        return adminService.addCategory(category);
    }

    @PostMapping("/addproduct")
    public Product addProduct(@RequestBody Product product) throws CategoryException, ProductException {

        return adminService.addProduct(product);

    }

    @GetMapping("/products")
    public List<Product> allProduct() throws ProductException {
        return adminService.products();
    }


    @GetMapping("/category")
    public List<Category> allCategory() throws CategoryException {
        return  adminService.categories();

    }


}
