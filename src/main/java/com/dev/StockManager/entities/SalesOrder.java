package com.dev.StockManager.entities;

import com.dev.StockManager.dtos.product.CreateShortProductAssociationDTO;
import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import jakarta.persistence.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class SalesOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal priceTotal;

    @Column(name = "date_moment")
    private Timestamp dateMoment;

    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "phone")
    private Phone phone;

    @OneToMany(mappedBy = "ps_id.salesOrder")
    private List<SalesOrderProduct> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    public SalesOrder() {
    }

    public SalesOrder(Integer id, Timestamp dateMoment, SalesOrderStatus status,
                      Address deliveryAddress, Phone phone, Client clientId) {
        this.id = id;
        this.dateMoment = dateMoment;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.client = clientId;
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
        return client;
    }

    public void setClientId(Client clientId) {
        this.client = clientId;
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

    public void calculateTotalPrice() {
        priceTotal = BigDecimal.valueOf(products.stream()
                .mapToDouble(x -> x.getPrice().doubleValue() * x.getQuantity()).sum());
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
