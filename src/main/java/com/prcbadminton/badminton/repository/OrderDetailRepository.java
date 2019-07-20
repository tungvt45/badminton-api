package com.prcbadminton.badminton.repository;

import com.prcbadminton.badminton.entities.OrderDetail;
import com.prcbadminton.badminton.entities.OrderDetailIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailIdentity> {
    List<OrderDetail> findAllByOrderDetailIdentity_OrderId(int orderId);
}
