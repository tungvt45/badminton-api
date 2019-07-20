package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.dto.OrderDTO;
import com.prcbadminton.badminton.entities.Order;
import com.prcbadminton.badminton.entities.OrderDetail;
import com.prcbadminton.badminton.entities.User;
import com.prcbadminton.badminton.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public List<OrderDetail> getOrderDetailById(int orderId) {
        return orderDetailRepository.findAllByOrderDetailIdentity_OrderId(orderId);
    }
}
