package com.prcbadminton.badminton.controller;

import com.prcbadminton.badminton.entities.OrderDetail;
import com.prcbadminton.badminton.services.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getOrderDetailById(@RequestParam int orderId) {
        List<OrderDetail> list = orderDetailService.getOrderDetailById(orderId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
