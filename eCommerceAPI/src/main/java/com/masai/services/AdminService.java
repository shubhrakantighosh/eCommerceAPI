package com.masai.services;


import com.masai.exceptions.AdminException;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.model.*;
import com.masai.repository.CategoryRepository;
import com.masai.repository.ProductRepository;
import com.masai.repository.UserRepository;
import com.masai.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;
    @Autowired
    private UserRepository userRepository;


    public List<ProductDTO> products() throws ProductException {

        List<ProductDTO>products=productRepository.products();

        if (products.size()==0){
            throw new ProductException("No product exists.");
        }else
            return products;

    }

    public Product addProduct(Product product) throws ProductException, CategoryException {

        if(categoryRepository.findById(product.getCategoryId()).isPresent()){
            boolean flag=true;
            List<Product>products= productRepository.findAll();

            for(Product i:products){
                if (i.getProductName().equalsIgnoreCase(product.getProductName())
                        && i.getProductPrice().equals(product.getProductPrice()))
                {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                product.setCategory(categoryRepository.findById(product.getCategoryId()).get());
                return productRepository.save(product);
            }else
                throw new ProductException("Product already exists.");
        }else

            throw new CategoryException("Category doesn't exists.");


    }

    public List<CategoryDTO> categories() throws CategoryException {

        List<CategoryDTO>categories= categoryRepository.categories();

        if (categories.size()==0){
            throw new CategoryException("No category exists.");
        }else
            return categories;
    }


    public Category addCategory(Category category) throws CategoryException {

        boolean flag=true;

        List<Category>categories= categoryRepository.findAll();
        for(Category i:categories){
            if (i.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
                flag = false;
                break;
            }
        }

        if(flag){
            return categoryRepository.save(category);

        }else
            throw new CategoryException("Category already exists.");

    }



    public List<UserSessionDTO> userSessions() throws AdminException {
        if (userSessionRepository.userSessions().isEmpty()){
            throw new AdminException("No user session is there.");
        }
        return userSessionRepository.userSessions();
    }

    public UserDTO searchByUserId(Integer userId){
        return userRepository.searchByUserId(userId);
    }

    public UserDTO searchByUserName(String userName) {

        return userRepository.searchByUserName(userName);
    }

    public List<UserDTO> users(){
        return userRepository.users();
    }

}
