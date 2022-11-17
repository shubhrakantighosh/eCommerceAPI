package com.masai.services;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.repository.AdminCategoryRepository;
import com.masai.repository.AdminProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminCategoryRepository adminCategoryRepository;
    @Autowired
    private AdminProductRepository adminProductRepository;


    public List<Product> products() throws ProductException {

        List<Product>products=adminProductRepository.findAll();

        if (products.size()==0){
            throw new ProductException("No product exists.");
        }else
            return products;

    }

    public Product addProduct(Product product) throws ProductException, CategoryException {

        if(adminCategoryRepository.findById(product.getCategoryId()).isPresent()){
            boolean flag=true;
            List<Product>products= adminProductRepository.findAll();

            for(Product i:products){
                if (i.getProductName().equalsIgnoreCase(product.getProductName())
                        && i.getProductPrice().equals(product.getProductPrice()))
                {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                product.setCategory(adminCategoryRepository.findById(product.getCategoryId()).get());
                return adminProductRepository.save(product);
            }else
                throw new ProductException("Product already exists.");
        }else

            throw new CategoryException("Category doesn't exists.");


    }

    public List<Category> categories() throws CategoryException {

        List<Category>categories= adminCategoryRepository.findAll();

        if (categories.size()==0){
            throw new CategoryException("No category exists.");
        }else
            return categories;
    }


    public Category addCategory(Category category) throws CategoryException {

        boolean flag=true;

        List<Category>categories= adminCategoryRepository.findAll();
        for(Category i:categories){
            if (i.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
                flag = false;
                break;
            }
        }

        if(flag){
            return adminCategoryRepository.save(category);

        }else
            throw new CategoryException("Category already exists.");

    }

}
