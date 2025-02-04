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


    @ManyToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "phone")
    private Phone phone;

    @JsonIgnore
    @OneToMany(mappedBy = "ps_id.salesOrder")
    private List<SalesOrderProduct> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    public SalesOrder() {
    }

    public SalesOrder(Integer id, Double priceTotal, Timestamp dateMoment, SalesOrderStatus status,
                      Address deliveryAddress, Phone phone, Client clientId) {
        this.id = id;
        this.priceTotal = priceTotal;
        this.dateMoment = dateMoment;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.clientId = clientId;
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

    public List<SalesOrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SalesOrderProduct> products) {
        this.products = products;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
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
