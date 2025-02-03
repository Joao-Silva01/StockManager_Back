package com.dev.StockManager.services;

import com.dev.StockManager.dtos.SalesOrderDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.repositories.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public List<SalesOrderDTO> allCustomerOrders(){
        List<SalesOrder> list = salesOrderRepository.findAll();
        List<SalesOrderDTO> listDTO =list.stream().map(SalesOrderDTO::new).toList();
        return listDTO;
    }
}
