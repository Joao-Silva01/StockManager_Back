package com.dev.StockManager.entities;

import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SalesOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Double priceTotal;

    @Column(name = "date_moment")
    private Timestamp dateMoment;

    @Enumerated(EnumType.ORDINAL)
    private SalesOrderStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "ps_id.salesOrder")
    private List<SalesOrderProduct> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    public SalesOrder() {
    }

    public SalesOrder(Integer id, Double priceTotal, Timestamp dateMoment, SalesOrderStatus status, Client client) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.dateMoment = dateMoment;
        this.status = status;
        this.clientId = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPriceTotal() {
        return priceTotal;
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

    public List<SalesOrderProduct> getItens() {
        return itens;
    }

    public void setItens(List<SalesOrderProduct> itens) {
        this.itens = itens;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesOrder that = (SalesOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
