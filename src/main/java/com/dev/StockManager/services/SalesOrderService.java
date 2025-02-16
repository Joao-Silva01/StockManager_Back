package com.dev.StockManager.services;

import com.dev.StockManager.converter.SalesOrderConverter;
import com.dev.StockManager.converter.SalesOrderProductConverter;
import com.dev.StockManager.dtos.product.CreateShortProductAssociationDTO;
import com.dev.StockManager.dtos.sales.SalesOrderShortDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.entities.*;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.repositories.*;
import com.dev.StockManager.validator.SalesOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return list.stream().map(SalesOrderDTO::new).toList();
    }

    public List<SalesOrderDTO> allCustomerOrders(Integer clientId) {
        clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));
        List<SalesOrder> sales = salesOrderRepository.findA(clientId);

        return sales.stream().map(SalesOrderDTO::new).toList();
    }

    @Transactional
    public void createOrder(Integer clientId, SalesOrderShortDTO salesDTO) {
        // Pegando os dados do banco de dados para validar
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));

        SalesOrderValidator.createOrderValidator(salesDTO, client);

        Address address = SalesOrderConverter.deliveryAddressAssociationConverter(salesDTO, client);
        Phone phone = SalesOrderConverter.phoneAssociationConverter(salesDTO, client);

        // lista para armazenar os dados originais dos produtos
        List<Product> products = listItensOrder(salesDTO);

        SalesOrder sales = SalesOrderConverter.toCreateEntity(salesDTO, address, phone, client, products);
        salesOrderRepository.save(sales);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toCreateEntity(salesDTO, sales, products);

        salesOrderProductRepository.saveAll(sop);

    }

    @Transactional
    public void updateOrder(Integer orderId, SalesOrderShortDTO salesDTO) {
        SalesOrder order = salesOrderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order not found"));
        SalesOrderValidator.statusValidator(order);
        SalesOrderValidator.updateOrderValidator(salesDTO, order.getClientId());
        SalesOrder sales = SalesOrderConverter.toUpdateEntity(order, salesDTO);

        List<Product> products = new ArrayList<>();

        if (salesDTO.getItens() != null) {
            products = listItensOrder(salesDTO);
        }

        salesOrderRepository.save(sales);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toUpdateEntity(salesDTO, sales, products);
        sales.setProducts(sop);

        sales.calculateTotalPrice();


        salesOrderProductRepository.saveAll(sop);
    }

    @Transactional
    public void deleteOrder(Integer orderId){
        salesOrderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order not found"));
        salesOrderRepository.deleteById(orderId);
    }


    public List<Product> listItensOrder(SalesOrderShortDTO salesDTO) {
        List<Product> products = new ArrayList<>();

        // Laço para adicionar todos os produtos do pedido na lista products
        for (CreateShortProductAssociationDTO cspa : salesDTO.getItens()) {
            products.add(productRepository.findById(cspa.getId()).orElseThrow(() -> new IdNotFoundException("Product not found")));
        }
        return products;
    }

}
