package com.dev.StockManager.dtos.sales;

import com.dev.StockManager.converter.SalesOrderConverter;
import com.dev.StockManager.dtos.product.CreateShortProductAssociationDTO;
import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.enums.SalesOrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class CreateSalesOrderDTO implements Serializable {

    private Integer id;

    private Integer clientId;

    private Integer deliveryAddress;

    private Integer phone;

    private List<CreateShortProductAssociationDTO> itens;

    private Timestamp dateMoment;

    private SalesOrderStatus status;

    public CreateSalesOrderDTO(){}

    public CreateSalesOrderDTO(SalesOrder order,Integer clientid, Integer deliveryAddress, Integer phone) {
        this.id = order.getId();
        this.dateMoment = order.getDateMoment();
        this.status = order.getStatus();
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.clientId = clientid;
        this.itens = order.getProducts().stream().map(x -> new CreateShortProductAssociationDTO(
                x.getProduct().getId(), x.getQuantity())).toList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<CreateShortProductAssociationDTO> getItens() {
        return itens;
    }

    public void setItens(List<CreateShortProductAssociationDTO> itens) {
        this.itens = itens;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Integer deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
