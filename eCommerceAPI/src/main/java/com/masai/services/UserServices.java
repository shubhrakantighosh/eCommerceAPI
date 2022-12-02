package com.masai.services;


import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.*;
import com.masai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserServices {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;

    public User registerUser(User user) throws UserException {
        boolean flag=true;

        List<User>users=userRepository.findAll();

        for(User i:users){

            if(i.getUserName().equalsIgnoreCase(user.getUserName())
            && i.getUserPassword().equals(user.getUserPassword())){
                flag=false;
                break;
            }

        }

        if(flag){

            return userRepository.save(user);
        }else
            throw new UserException("You already registered.");
    }

    public String logIn(String username, String password) throws UserException {

        List<UserSession>usersessions=userSessionRepository.findAll();

        for (UserSession usersession:usersessions) {

            if (usersession.getEnd() == null) {
                throw new UserException("Please Log Out.");

            }
        }

        User user = null;

        boolean flag = true;

        List<User> users = userRepository.findAll();
        for (User i : users) {
            if (i.getUserName().equalsIgnoreCase(username) &&
                    !i.getUserPassword().equals(password)) {
                throw new UserException("Wrong Password.");

            } else if (i.getUserName().equalsIgnoreCase(username) &&
                    i.getUserPassword().equals(password)) {
                flag = false;
                user = i;
                break;
            }
        }

        if (flag) {
            throw new UserException("You are not registered with us.");

        } else {

            UserSession userSession = new UserSession();
            userSession.setStart(LocalDateTime.now());
            userSession.setUser(user);
            userSessionRepository.save(userSession);

            return "LogIn successful.";
        }

    }


    public String logout() throws UserException {

        List<UserSession>userSessions=userSessionRepository.findAll();

        boolean flag=false;
        UserSession session=null;

        for(UserSession userSession:userSessions){
            if(userSession.getEnd()==null){
                flag=true;
                session=userSession;
                break;
            }

        }

        if (flag){
            session.setEnd(LocalDateTime.now());
            userSessionRepository.save(session);
            return "Log Out successfully";
        }else
            throw new UserException("No Session active.");

    }


    public List<CategoryDTO> categories() throws CategoryException {

        List<CategoryDTO>categories=categoryRepository.categories();

        if(categories.size()==0){
            throw new CategoryException("No category exists.");
        }else
            return categories;
    }

    public List<ProductDTO> products() throws ProductException {

        List<ProductDTO>products=productRepository.products();

        if (products.size()==0){
            throw new ProductException("No product exists.");
        }else
            return products;

    }


    public List<ProductDTO> searchByCategoryName(String categoryName) throws CategoryException, ProductException {
        Integer categoryId=categoryRepository.findCategoryByName(categoryName);
        if (categoryId==null){
            throw new CategoryException("No category exists.");
        }
        List<ProductDTO>productDTOList=productRepository.productsSearchByCategoryId(categoryId);

        if (productDTOList.isEmpty()){
            throw new ProductException("No Product exists.");
        }else
            return productDTOList;

    }

    public List<ProductDTO> searchByProductPrice(Double minprice,Double maxprice) throws ProductException, UserException {

        if (minprice>maxprice){
            throw new UserException("Minimum Price should less from the Maximum Price");
        }

        List<ProductDTO>productDTOList=productRepository.searchByProductPrice(minprice,maxprice);

        if (productDTOList.isEmpty()){
            throw new ProductException("No Product exists.");
        }else
            return productDTOList;

    }

    public ProductDTO viewProductByName(String name) throws ProductException {
        ProductDTO productDTO=productRepository.viewProductByName(name);

        if (productDTO==null){
            throw new ProductException("No Product exists.");
        }else
            return productDTO;

    }

    public String addAddress(Address address) throws UserException {

        boolean flag=true;
        User user=null;
        List<UserSession> userSessions=userSessionRepository.findAll();

        for (UserSession userSession:userSessions){

            if(userSession.getUser()!=null && userSession.getEnd()==null){
                user=userSession.getUser();
                flag=false;
                break;
            }

        }

        if (flag){

            throw new UserException("Please LogIn.");

        } else if (user.getAddress()!=null) {

            throw new UserException("Address Already registered.");

        } else {
            user.setAddress(address);
            userRepository.save(user);
        }

            return "Registered successfully.";
    }

    public String addToCart(Integer productId) throws UserException {

        boolean flag = true;
        User user = null;
        Cart cart = new Cart();

        for (UserSession userSession : userSessionRepository.findAll()) {

            if (userSession.getStart() != null && userSession.getEnd() == null) {

                user=userSession.getUser();

                if (user.getAddress() == null) {
                    throw new UserException("Please Register your address.");
                } else {

                    Optional <Product>product=productRepository.findById(productId);

                    if (product.isEmpty()){
                        throw new UserException("No Product exists.");
                    }

                    List<Cart> userCarts= new ArrayList<>();

                    for (Cart carts : cartRepository.findAll()) {
                        if (carts.getUser() == user) {
                            userCarts.add(carts);
                        }
                    }

                    for (Cart carts : userCarts) {
                        if (carts.getOrders()== null) {
                            cart = carts;
                            break;
                        }
                    }

                    cart.setUser(user);
                    cart.getProducts().add(product.get());

                    cartRepository.save(cart);
                    flag = false;
                }
            }
        }

        if (flag) {
            throw new UserException("Please Log In.");
        } else
            return "Added.";

    }


    public String totalAmount() throws UserException {

        User user=null;
        List<UserSession> userSessions=userSessionRepository.findAll();

        for (UserSession userSession:userSessions){

            if(userSession.getUser()!=null && userSession.getEnd()==null){
                user=userSession.getUser();
                break;
            }

        }

        if (user==null){
            throw new UserException("Please LogIn.");
        }

        List<Product>total=new ArrayList<>();

        for (Cart cart:cartRepository.findAll()){
            if (cart.getUser()==user && cart.getOrders()==null){
                total.addAll(cart.getProducts());
            }
        }

        if (total.isEmpty()){
            throw new UserException("No Product added.");
        }

        Double sum= 0.00;

        for(Product product:total){
            sum+=product.getProductPrice();
        }

        return "Total Amount is "+sum;

    }


    public String removeCart() throws UserException {

        User user=null;

        for (UserSession userSession:userSessionRepository.findAll()){

            if(userSession.getUser()!=null && userSession.getEnd()==null){
                user=userSession.getUser();
                break;
            }

        }

        if (user==null){
            throw new UserException("Please LogIn.");
        }

        List<Cart> carts=user.getCarts();

        Cart cart=null;

        for (Cart cart1:carts) {
            if (cart1.getOrders()==null){
                cart=cart1;
                break;
            }
        }


        if (cart == null) {
            throw new UserException("No Product added.");
        }

        cartRepository.delete(cart);


        return "Removed Successfully.";

    }

    public Orders orderCreated(Payment paymentMode) throws UserException {

        User user=null;

        for (UserSession userSession:userSessionRepository.findAll()){

            if(userSession.getUser()!=null && userSession.getEnd()==null){
                user=userSession.getUser();
                break;
            }

        }

        if (user==null){
            throw new UserException("Please LogIn.");
        }

        List<Cart> carts=user.getCarts();

        Cart cart=null;

        for (Cart cart1:carts){
            if (cart1.getOrders()==null){
                cart=cart1;
                break;
            }
        }


        if (cart==null){
            throw new UserException("No Product added.");
        }

        Orders orders= new Orders();
        orders.setCart(cart);
        orders.setPayment(paymentMode);

        cart.setOrders(orders);

        return orderStatusRepository.save(orders);
    }


}
