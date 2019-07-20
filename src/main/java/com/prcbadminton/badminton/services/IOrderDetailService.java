package com.prcbadminton.badminton.services;

import com.prcbadminton.badminton.entities.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getOrderDetailById(int orderId);
}
