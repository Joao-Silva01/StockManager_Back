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

        return list.stream().map(x -> new SalesOrderDTO(x)).toList();
    }

    public List<SalesOrderDTO> allCustomerOrders(Integer clientId) {
        clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));
        List<SalesOrder> sales = salesOrderRepository.findA(clientId);

        return sales.stream().map(x -> new SalesOrderDTO(x)).toList();
    }

    @Transactional
    public void createOrder(Integer clientId, SalesOrderShortDTO order) {
        // Pegando os dados do banco de dados para validar
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client id not found"));

        SalesOrderValidator.createOrderValidator(order, client);

        Address address = SalesOrderConverter.deliveryAddressAssociationConverter(order, client);
        Phone phone = SalesOrderConverter.phoneAssociationConverter(order, client);

        List<Product> products = listItensOrder(order); // lista para armazenar os dados originais dos produtos

        SalesOrder so = SalesOrderConverter.toCreateEntity(order, address, phone, client, products);
        salesOrderRepository.save(so);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toCreateEntity(order, so, products);

        salesOrderProductRepository.saveAll(sop);

    }

    @Transactional
    public void updateOrder(Integer orderId, SalesOrderShortDTO sales) {
        SalesOrder order = salesOrderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order not found"));
        SalesOrderValidator.statusValidator(order);
        SalesOrderValidator.updateOrderValidator(sales, order.getClientId());
        SalesOrder save = SalesOrderConverter.toUpdateEntity(order, sales);

        List<Product> products = new ArrayList<>();

        if (sales.getItens() != null) {
            products = listItensOrder(sales);
        }

        salesOrderRepository.save(save);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toUpdateEntity(sales, save, products);
        save.setProducts(sop);

        save.calculateTotalPrice();


        salesOrderProductRepository.saveAll(sop);
    }

    @Transactional
    public void deleteOrder(Integer orderId){
        salesOrderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order not found"));
        salesOrderRepository.deleteById(orderId);
    }


    public List<Product> listItensOrder(SalesOrderShortDTO sos) {
        List<Product> products = new ArrayList<>();

        // Laço para adicionar todos os produtos do pedido na lista products
        for (CreateShortProductAssociationDTO cspa : sos.getItens()) {
            products.add(productRepository.findById(cspa.getId()).orElseThrow(() -> new IdNotFoundException("Product not found")));
        }
        return products;
    }

}
