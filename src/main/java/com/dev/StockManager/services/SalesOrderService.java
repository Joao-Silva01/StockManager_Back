package com.dev.StockManager.services;

import com.dev.StockManager.dtos.SalesOrderDTO;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.mapper.SalesOrderMapper;
import com.dev.StockManager.repositories.ClientRepository;
import com.dev.StockManager.repositories.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<SalesOrderDTO> allOrders(){
        List<SalesOrder> list = salesOrderRepository.findAll();
        List<SalesOrderDTO> listDTO =list.stream().map(x -> new SalesOrderDTO(x)).toList();
        return listDTO;
    }

    public List<SalesOrderDTO> allCustomerOrders(Integer clientId){
        clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));

        List<SalesOrder> sales = salesOrderRepository.findA(clientId);
        List<SalesOrderDTO> dto = sales.stream().map(x -> new SalesOrderDTO(x)).toList();
        return dto;
    }
}
