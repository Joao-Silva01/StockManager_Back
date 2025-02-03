package com.dev.StockManager.dtos;

import com.dev.StockManager.dtos.client.ClientShortDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.dev.StockManager.mapper.ClientMapper;
import com.dev.StockManager.mapper.SalesOrderMapper;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class SalesOrderDTO implements Serializable {

    private Integer id;

    private ClientShortDTO clientId;

    private AddressDTO deliveryAddress;

    private PhoneDTO phone;

    private List<ProductDTO> itens;

    private Double priceTotal;

    private Timestamp dateMoment;

    private SalesOrderStatus status;

    public SalesOrderDTO(){}

    public SalesOrderDTO(SalesOrder order) {
        this.id = order.getId();
        this.priceTotal = order.getPriceTotal();
        this.dateMoment = order.getDateMoment();
        this.status = order.getStatus();
        this.deliveryAddress = SalesOrderMapper.addressConversion(order);
        this.phone = SalesOrderMapper.phoneConversion(order);
        this.clientId = ClientMapper.simpleDataConversion(order.getClientId()); // realiza a conversão do Client pro ClientShortDTO
        this.itens = SalesOrderMapper.itensConversion(order);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getPriceTotal() {
        double total = 0;
        for (ProductDTO p : itens) {
            total += p.getPrice() * p.getQuantity();
        }
        return total;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Timestamp getDateMoment() {
        return dateMoment;
    }

    public void setDateMoment(Timestamp dateMoment) {
        this.dateMoment = dateMoment;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public void setStatus(SalesOrderStatus status) {
        this.status = status;
    }

    public List<ProductDTO> getItens() {
        return itens;
    }

    public void setItens(List<ProductDTO> itens) {
        this.itens = itens;
    }

    public ClientShortDTO getClientId() {
        return clientId;
    }

    public void setClientId(ClientShortDTO clientId) {
        this.clientId = clientId;
    }

    public AddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneDTO phone) {
        this.phone = phone;
    }
}
