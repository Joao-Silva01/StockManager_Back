package com.dev.StockManager.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String streetName; // nome da rua
    private String complement;
    private String neighborhoodName; // nome do bairro
    private Integer number;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    public Address() {
    }

    public Address(Integer id, String streetName, String complement, String neighborhoodName, Integer number, String cep, Client client) {
        this.id = id;
        this.streetName = streetName;
        this.complement = complement;
        this.neighborhoodName = neighborhoodName;
        this.number = number;
        this.cep = cep;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
