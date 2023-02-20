package com.masai.services;


import com.masai.DTO.CategoryDTO;
import com.masai.DTO.OrderDTO;
import com.masai.DTO.OrderedProductDTO;
import com.masai.DTO.ProductDTO;
import com.masai.repository.CustomQuery.Delete_Rows;
import com.masai.exceptions.CategoryException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.UserException;
import com.masai.model.*;
import com.masai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class UserServices {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private AddressRepository addressRepository;

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
                        if (carts.getUser()== user) {
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
            if (cart.getUser()==user){
                total.addAll(cart.getProducts());
            }
        }

        if (total.isEmpty()){
            throw new UserException("No Product added.");
        }

        Double sum= 0.00;
        String items="";

        for(Product product:total){

            sum+=product.getProductPrice();

            items+="Product Name : "+product.getProductName()+"\n";
            items+="Product Price : "+product.getProductPrice()+"\n";
            items+="Product include gst : "+((int)(product.getProductPrice()+(product.getProductPrice()*18)/100))+"\n";
            items+="\n";
            items+="   <<=============>>"+"\n";
            items+="\n";
        }

        if(sum>=5000){
            items+="Total : "+(sum+((int)(sum*18)/100))+"\n";
            items+="\n"+"Free Shipping.";
        }else {
            items+="Total : "+((sum+((int)(sum*18)/100))+400)+"\n";
            items+="\n"+"Shipping Charges : 400";
        }

        return items;

    }


    public String removeCart() throws UserException, CategoryException {

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


        if (carts.size() == 0) {
            throw new UserException("No Product added.");
        }

        Delete_Rows.delete_rows(carts.get(0).getCardId());

//        new Delete_Rows().delete_row(cart.getCardId());


        return "Removed Successfully.";

    }

    public String orderPlace(Payment paymentMode) throws UserException, CategoryException {

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


        if (carts.size()==0){
            throw new UserException("No Product added.");
        }


        Double totalPrice=0.0;

        Orders orders =new Orders();

        List<Product>products=carts.get(0).getProducts();

        List<OrderedProduct> orderedProducts =new ArrayList<>();

        for(Product product : products){


            OrderedProduct orderedProduct =new OrderedProduct();

            orderedProduct.setProductName(product.getProductName());
            orderedProduct.setProductPrice(product.getProductPrice());
            orderedProduct.setGst((int) (((product.getProductPrice())*18)/100));
            orderedProduct.setOrders(orders);
            orderedProduct.setTotalPrice(orderedProduct.getProductPrice()+orderedProduct.getGst());


            totalPrice+=orderedProduct.getTotalPrice();

            orderedProducts.add(orderedProduct);

        }

        orders.setProducts(orderedProducts);
        orders.setPayment(paymentMode);
        orders.setUser(user);
        orders.setTotalPrice(totalPrice);
        orders.setPinCode(user.getAddress().getPinCode());
        orders.setCity(user.getAddress().getCity());
        orders.setState(user.getAddress().getState());

        if(orders.getTotalPrice()>=5000){
            orders.setShipping_Charges("Free");
        }else orders.setShipping_Charges("400");

        orderRepository.save(orders);

        Delete_Rows.delete_rows(carts.get(0).getCardId());


        return "Order Place Successfully.";
    }

    public HashMap<OrderDTO, List<OrderedProductDTO>> orderSummary() throws UserException, ProductException {

        boolean flag = true;
        User user = null;
        List<UserSession> userSessions = userSessionRepository.findAll();

        for (UserSession userSession : userSessions) {

            if (userSession.getUser() != null && userSession.getEnd() == null) {
                user = userSession.getUser();
                flag = false;
                break;
            }

        }

        if (flag) {

            throw new UserException("Please LogIn.");
        }

        List<Orders> ordersList = orderRepository.findAll();

        List<Orders>list=new ArrayList<>();

        for(Orders order : ordersList){

            if (Objects.equals(order.getUser().getUserId(), user.getUserId())){
                list.add(order);
            }

        }

        if (list.isEmpty()){
            throw new ProductException("No Product Found.");
        }


        HashMap<OrderDTO, List<OrderedProductDTO>>hashMap=new HashMap<>();

        for(Orders order: list){

            OrderDTO dto=new OrderDTO();

            dto.setOrderId(order.getOrderId());
            dto.setTotalPrice(order.getTotalPrice());
            dto.setPayment(order.getPayment());
            dto.setShipping_Charges(order.getShipping_Charges());

            List<OrderedProductDTO>dos=orderedProductRepository.orders(order.getOrderId());

            hashMap.put(dto,dos);

        }


        return hashMap;

    }

    public String updateAddress(String pinCode, String city,String state) throws UserException {

        boolean flag = true;
        User user = null;
        List<UserSession> userSessions = userSessionRepository.findAll();

        for (UserSession userSession : userSessions) {

            if (userSession.getUser() != null && userSession.getEnd() == null) {
                user = userSession.getUser();
                flag = false;
                break;
            }

        }

        if (flag) {

            throw new UserException("Please LogIn.");
        }

        if (user.getAddress()==null){
            throw new UserException("Please Register your Address.");
        }


        Address address=new Address();
        address.setAddressId(user.getAddress().getAddressId());
        address.setPinCode(pinCode);
        address.setCity(city);
        address.setState(state);

        addressRepository.save(address);


        return "Updated.";


    }
}
