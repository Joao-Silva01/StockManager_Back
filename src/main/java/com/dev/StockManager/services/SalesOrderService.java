package com.dev.StockManager.services;

import com.dev.StockManager.converter.SalesOrderConverter;
import com.dev.StockManager.converter.SalesOrderProductConverter;
import com.dev.StockManager.dtos.product.CreateShortProductAssociationDTO;
import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.entities.*;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private SalesOrderProductRepository salesOrderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<SalesOrderDTO> allOrders() {
        List<SalesOrder> list = salesOrderRepository.findAll();
        List<SalesOrderDTO> listDTO = list.stream().map(x -> new SalesOrderDTO(x)).toList();
        return listDTO;
    }

    public List<SalesOrderDTO> allCustomerOrders(Integer clientId) {
        clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));

        List<SalesOrder> sales = salesOrderRepository.findA(clientId);
        List<SalesOrderDTO> dto = sales.stream().map(x -> new SalesOrderDTO(x)).toList();
        return dto;
    }

    public void createOrder(Integer clientId, CreateSalesOrderDTO order) {

        // Pegando os dados do banco de dados para validar
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));
        Address address = addressRepository.findById(order.getDeliveryAddress()).orElseThrow(() -> new IdNotFoundException("Address not found"));
        Phone phone = phoneRepository.findById(order.getPhone()).orElseThrow(() -> new IdNotFoundException("Phone not found"));

        List<Product> products = new ArrayList<>(); // lista para armazenar os dados originais dos produtos

        // Laço para adicionar todos os produtos do pedido na lista products
        for (int i =1 ; i <=  order.getItens().size() ; i++) {
            products.add(productRepository.findById(i).get());
        }

        SalesOrder so = SalesOrderConverter.toCreateEntity(order, address, phone, client, products);
        salesOrderRepository.save(so);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toCreateEntity(order,so, products);

        salesOrderProductRepository.saveAll(sop);

    }



}
