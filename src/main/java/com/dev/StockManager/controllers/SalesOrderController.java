package com.dev.StockManager.controllers;

import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.dtos.sales.UpdateSalesOrderDTO;
import com.dev.StockManager.services.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesService;

    @GetMapping
    public ResponseEntity<List<SalesOrderDTO>> allCustomerOrders (){
        var list = salesService.allOrders();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{clientId}")
    public ResponseEntity<?> create (@PathVariable Integer clientId, @RequestBody CreateSalesOrderDTO body){
        salesService.createOrder(clientId,body);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateSalesOrderDTO dto){
        salesService.UpdateOrder(id, dto);
        return ResponseEntity.ok().build();
    }


}
