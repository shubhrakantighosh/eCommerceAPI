package com.masai.controller;


import com.masai.exceptions.AdminException;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.model.*;
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

    @GetMapping("/userSession")
    public ResponseEntity<List<UserSessionDTO>> userSessions() throws AdminException {
        return new ResponseEntity<>(adminService.userSessions(),HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> users(){
        return new ResponseEntity<>(adminService.users(),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public UserDTO searchByUserId(@PathVariable Integer userId){
        return adminService.searchByUserId(userId);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserDTO> searchByUserId(@PathVariable String userName) {
        return new ResponseEntity<>(adminService.searchByUserName(userName),HttpStatus.OK);
    }


}
