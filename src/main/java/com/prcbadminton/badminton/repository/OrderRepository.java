package com.prcbadminton.badminton.repository;

import com.prcbadminton.badminton.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
