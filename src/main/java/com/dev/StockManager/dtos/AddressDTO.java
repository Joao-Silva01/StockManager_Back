package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Address;
import com.dev.StockManager.entities.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {

    @JsonIgnore
    private Integer id;

    @NotBlank(message = "streetName - cannot be null or empty")
    @Column(length = 100)
    private String streetName;

    @Column(length = 150)
    private String complement;

    @NotBlank(message = "neighborhoodName - cannot be null or empty")
    @Column(length = 100)
    private String neighborhoodName;

    @NotNull(message = "number - cannot be null")
    private Integer number;

    @NotBlank(message = "cep - cannot be null or empty")
    @Column(length = 8)
    private String cep;

    @JsonIgnore
    private Client client;

    public AddressDTO() {
    }

    public AddressDTO(Address entity) {
        this.id = entity.getId();
        this.streetName = entity.getStreetName();
        this.complement = entity.getComplement();
        this.neighborhoodName = entity.getNeighborhoodName();
        this.number = entity.getNumber();
        this.cep = entity.getCep();
        this.client = entity.getClient();
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


}
