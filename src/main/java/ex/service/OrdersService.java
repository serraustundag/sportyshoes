package ex.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ex.entity.Product;
import ex.repository.ProductRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ex.entity.Orders;
import ex.repository.OrdersRepository;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductRepository productRepository;

    public String placeOrder(Orders orders,int userId) {
        // oid auto increment we need only pid as fk
        orders.setLdt(LocalDateTime.now());
        ordersRepository.save(orders);
        return "Order placed successfully for product "+orders.getPid();
    }

    public List<Orders> getOrdersByCategory(String category) {
        List<Orders> orderList = new ArrayList<>();
        List<Integer> pidList = productRepository.findPidByCategory(category);

        for(Integer pid : pidList){
            orderList.addAll(ordersRepository.findOrdersByPid(pid));
        }

        return orderList;

    }

    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    public List<Orders> getOrdersByDateRange(LocalDateTime startDate) {
        return ordersRepository.findByLdtAfter(startDate);
    }
}