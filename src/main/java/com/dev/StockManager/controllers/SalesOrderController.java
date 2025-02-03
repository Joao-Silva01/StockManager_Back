package com.dev.StockManager.controllers;

import com.dev.StockManager.dtos.SalesOrderDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.services.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesService;

    @GetMapping
    public ResponseEntity<List<SalesOrderDTO>> allCustomerOrders (){
        var list = salesService.allCustomerOrders();

        return ResponseEntity.ok().body(list);
    }
}
