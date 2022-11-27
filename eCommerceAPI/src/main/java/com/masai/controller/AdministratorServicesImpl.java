package com.masai.controller;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.model.Category;
import com.masai.model.CategoryDTO;
import com.masai.model.Product;
import com.masai.model.ProductDTO;
import com.masai.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdministratorServicesImpl {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addcategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException {
        return new ResponseEntity<>(adminService.addCategory(category),HttpStatus.OK);
    }

    @PostMapping("/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws CategoryException, ProductException {

        return new ResponseEntity<>(adminService.addProduct(product),HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> product() throws ProductException {
        return new ResponseEntity<>(adminService.products(), HttpStatus.OK);
    }


    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> categories() throws CategoryException {
        return  new ResponseEntity<>(adminService.categories(),HttpStatus.OK);

    }


}
