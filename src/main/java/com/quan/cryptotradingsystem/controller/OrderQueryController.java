package com.quan.cryptotradingsystem.controller;

import com.quan.cryptotradingsystem.model.OrderHistoryModel;
import com.quan.cryptotradingsystem.service.OrderQueryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    @Autowired
    public OrderQueryController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/order-histories")
    public ResponseEntity<List<OrderHistoryModel>> getLatestPrices() {
        return ResponseEntity.ok(orderQueryService.getOrderHistories());
    }
}
