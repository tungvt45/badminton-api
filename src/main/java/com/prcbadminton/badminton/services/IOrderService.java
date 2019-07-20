package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.dto.OrderDTO;
import com.prcbadminton.badminton.entities.User;
import com.prcbadminton.badminton.entities.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(List<OrderDTO> orderList, User user);
    List<Order> getAll() throws Exception;
}
