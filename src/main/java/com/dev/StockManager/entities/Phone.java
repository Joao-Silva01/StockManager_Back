package com.dev.StockManager.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client clientId;

    public Phone() {
    }

    public Phone(Integer id, Integer number, Client clientId) {
        this.id = id;
        this.number = number;
        this.clientId = clientId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
