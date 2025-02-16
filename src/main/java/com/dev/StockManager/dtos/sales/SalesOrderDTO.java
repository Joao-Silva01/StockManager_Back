package com.dev.StockManager.dtos.sales;

import com.dev.StockManager.converter.AddressConverter;
import com.dev.StockManager.converter.PhoneConverter;
import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.dtos.client.ClientShortDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.dev.StockManager.converter.ClientConverter;
import com.dev.StockManager.converter.SalesOrderConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class SalesOrderDTO implements Serializable {

    private Integer orderId;

    private ClientShortDTO client;

    private AddressDTO deliveryAddress;

    private PhoneDTO phone;

    private List<ProductDTO> products;

    @Column(scale = 10, precision = 2)
    private BigDecimal priceTotal;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    private Timestamp dateMoment;

    private SalesOrderStatus status;

    public SalesOrderDTO(){}

    public SalesOrderDTO(SalesOrder order) {
        this.orderId = order.getId();
        this.priceTotal = order.getPriceTotal();
        this.dateMoment = order.getDateMoment();
        this.status = order.getStatus();
        this.deliveryAddress = AddressConverter.deliveryConversion(order);
        this.phone = PhoneConverter.phoneSalesConversion(order);
        this.client = ClientConverter.shortConversion(order.getClientId()); // realiza a conversão do Client pro ClientShortDTO
        this.products = SalesOrderConverter.itensOrderConversion(order);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public ClientShortDTO getClient() {
        return client;
    }

    public void setClient(ClientShortDTO client) {
        this.client = client;
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
