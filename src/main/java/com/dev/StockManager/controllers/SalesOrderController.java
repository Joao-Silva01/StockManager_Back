package com.dev.StockManager.controllers;

import com.dev.StockManager.dtos.sales.SalesOrderShortDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.services.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesService;

    @GetMapping // ADMIN
    public ResponseEntity<List<SalesOrderDTO>> allCustomerOrders (){
        var list = salesService.allOrders();

        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{clientId}") // ALL
    public ResponseEntity<?> create (@PathVariable Integer clientId, @RequestBody SalesOrderShortDTO body){
        salesService.createOrder(clientId,body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}") // ALL
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SalesOrderShortDTO dto){
        salesService.updateOrder(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{orderId}") // ALL
    public ResponseEntity<?> delete(@PathVariable Integer orderId){
        salesService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }


}
