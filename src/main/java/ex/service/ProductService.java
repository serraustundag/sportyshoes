package ex.service;

import java.util.List;
import java.util.Optional;

import ex.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex.entity.Product;
import ex.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrdersRepository ordersRepository;


    public List<Product> findAllProducts() {
        productRepository.flush();
        return productRepository.findAll();		// select * from product in SQL
    }											// select p from Product p in HQL/JPQL


    public String storeProduct(Product product) {

        Optional<Product> result=productRepository.findById(product.getPid());
        if(result.isPresent()) {
            return "Product id must be unique";
        }else {
            productRepository.save(product);
            return "Product record stored successfully";
        }
    }

    public String deleteProduct(int pid) {

        System.out.println("product id is "+pid);
        Optional<Product> result=productRepository.findById(pid);
        if(result.isPresent()) {
            Product p = result.get();
            productRepository.delete(p);
            return "Product deleted successfully";
        }else {
            return "Product record not present";
        }
    }

    public Product searchProductById(int pid) {

        Optional<Product> result=productRepository.findById(pid);
        if(result.isPresent()) {
            Product product = result.get();
            return product;
        }else {
            return null;
        }
    }

    public String updateProduct(Product product) {
        Optional<Product> result=productRepository.findById(product.getPid());

        if(result.isPresent()) {
            Product p = result.get();
            p.setPname(product.getPname());
            p.setPrice(product.getPrice());
            p.setCategory(product.getCategory());
            p.setQuantity(product.getQuantity());
            productRepository.saveAndFlush(p);
            return "Product updated successfully";
        }else {
            return "Product record not present";
        }
    }

    public List<Object[]> orderDetails() {
        productRepository.flush();
        return productRepository.orderDetails();
        // custom methods
    }

    public List<Object[]> getOrdersByUserId(int userId) {
        return ordersRepository.findOrdersByUserId(userId);
    }
    public List<Object[]> getOrdersByUserId2() {
        return ordersRepository.findOrdersByUserId2();
    }

    public List<String> getAllCategories() {
        return productRepository.findDistinctByCategory();
    }
 }




