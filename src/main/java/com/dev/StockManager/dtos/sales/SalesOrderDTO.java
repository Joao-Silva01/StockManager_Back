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
import jakarta.persistence.Column;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class SalesOrderDTO implements Serializable {

    private Integer id;

    private ClientShortDTO clientId;

    private AddressDTO deliveryAddress;

    private PhoneDTO phone;

    private List<ProductDTO> itens;

    @Column(scale = 10, precision = 2)
    private BigDecimal priceTotal;

    private Timestamp dateMoment;

    private SalesOrderStatus status;

    public SalesOrderDTO(){}

    public SalesOrderDTO(SalesOrder order) {
        this.id = order.getId();
        this.priceTotal = order.getPriceTotal();
        this.dateMoment = order.getDateMoment();
        this.status = order.getStatus();
        this.deliveryAddress = AddressConverter.deliveryConversion(order);
        this.phone = PhoneConverter.phoneSalesConversion(order);
        this.clientId = ClientConverter.shortConversion(order.getClientId()); // realiza a conversão do Client pro ClientShortDTO
        this.itens = SalesOrderConverter.itensOrderConversion(order);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
