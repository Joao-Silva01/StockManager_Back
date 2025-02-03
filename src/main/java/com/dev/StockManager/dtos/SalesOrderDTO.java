package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.SalesOrderProduct;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SalesOrderDTO {
    private Integer id;

    private BigDecimal priceTotal;


    private Timestamp dateMoment;


    private SalesOrderStatus status;

    private List<ProductDTO> itens = new ArrayList<>();

    @JsonIgnore
    private Client clientId;

    public SalesOrderDTO(SalesOrder order) {
        this.id = order.getId();
        this.priceTotal = order.getPriceTotal();
        this.dateMoment = order.getDateMoment();
        this.status = order.getStatus();
        this.clientId = order.getClientId();
        this.itens =  order.getItens().stream()
                .map(x ->
                        new ProductDTO(x.getProduct().getId(),x.getProduct().getName(),
                                x.getProduct().getDescription(), x.getPrice(),
                                x.getProduct().getCategory_id().getCode(),x.getQuantity()))
                .toList();
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

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

}
