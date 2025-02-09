package com.dev.StockManager.services;

import com.dev.StockManager.converter.SalesOrderConverter;
import com.dev.StockManager.converter.SalesOrderProductConverter;
import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.product.CreateShortProductAssociationDTO;
import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.dtos.sales.UpdateSalesOrderDTO;
import com.dev.StockManager.entities.*;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.repositories.*;
import com.dev.StockManager.validator.SalesOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Address address = new Address();
        Phone phone = new Phone();

        if (order.getDeliveryAddress() > client.getAddresses().size() - 1) {
            throw new IdNotFoundException("Address not found");
        }

        if (order.getPhone() > client.getPhones().size() - 1) {
            throw new IdNotFoundException("Phone not found");
        }

        for (int i = 0; i < client.getAddresses().size(); i++) {
            if (order.getDeliveryAddress() == i) {
                address = Optional.of(client.getAddresses().get(order.getDeliveryAddress())).orElseThrow(() -> new IdNotFoundException("Address not found"));
            }
        }
        for (int i = 0; i < client.getPhones().size(); i++) {
            if (order.getPhone() == i) {
                phone = Optional.of(client.getPhones().get(order.getPhone())).orElseThrow(() -> new IdNotFoundException("Phone not found"));
            }
        }

        List<Product> products = new ArrayList<>(); // lista para armazenar os dados originais dos produtos

        // Laço para adicionar todos os produtos do pedido na lista products
        for (int i = 1; i <= order.getItens().size(); i++) {
            products.add(productRepository.findById(i).get());
        }

        SalesOrder so = SalesOrderConverter.toCreateEntity(order, address, phone, client, products);
        salesOrderRepository.save(so);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toCreateEntity(order, so, products);

        salesOrderProductRepository.saveAll(sop);

    }

    public void UpdateOrder(Integer orderId, UpdateSalesOrderDTO sales) {
        SalesOrder order = salesOrderRepository.findById(orderId).orElseThrow(() -> new IdNotFoundException("Order not found"));
        SalesOrderValidator.validatorStatus(order);

        List<Product> products = new ArrayList<>();

        if (sales.getItens() != null) {

            for (CreateShortProductAssociationDTO cspa : sales.getItens()) {
                products.add(productRepository.findById(cspa.getId()).get());
            }
        }

        SalesOrder save = SalesOrderConverter.toUpdateEntity(order, sales);

        salesOrderRepository.save(save);

        List<SalesOrderProduct> sop = SalesOrderProductConverter.toUpdateEntity(sales, save, products);

        salesOrderProductRepository.saveAll(sop);
    }


}
