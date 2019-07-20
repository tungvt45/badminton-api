package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.dto.OrderDTO;
import com.prcbadminton.badminton.entities.*;
import com.prcbadminton.badminton.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prcbadminton.badminton.entities.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BestSalesRepository bestSalesRepository;
    @Override
    public void createOrder(List<OrderDTO> orderList, User user) {
        Order order = new Order();
        order.setUser_id(user);
        Order newOrder = orderRepository.save(order);
        for (int i = 0; i < orderList.size(); i++) {
            Optional<Product> product = productRepository.findById(orderList.get(i).getId());
            OrderDetail orderDetail = new OrderDetail();
            OrderDetailIdentity orderDetailIdentity = new OrderDetailIdentity();
            orderDetailIdentity.setOrder(newOrder);
            orderDetailIdentity.setProduct(product.get());
            orderDetail.setOrderDetailIdentity(orderDetailIdentity);
            orderDetail.setQuantity(orderList.get(i).getCount());
            orderDetail.setMoney(orderList.get(i).getPrice());
            orderDetailRepository.save(orderDetail);
            Optional<BestSales> bestSales = bestSalesRepository.findBestSalesByProduct(product.get().getId());
            if (bestSales.isPresent()) {
                BestSales updateBestSales = bestSales.get();
                updateBestSales.setQuantity(bestSales.get().getQuantity() + orderList.get(i).getCount());
                bestSalesRepository.save(updateBestSales);
            } else {
                BestSales updateBestSales = new BestSales();
                updateBestSales.setQuantity(orderList.get(i).getCount());
                updateBestSales.setProduct_id(product.get());
            }
        }
    }
    
    @Override
    public List<Order> getAll() throws Exception{
        return orderRepository.findAll();
    }
}
