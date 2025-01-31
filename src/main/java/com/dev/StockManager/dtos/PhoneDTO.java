package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class PhoneDTO implements Serializable {

    @JsonIgnore
    private Integer id;

    @Size(min = 11, max = 11, message = "The phone size must be 11 (with area code)")
    private String number;
    @JsonIgnore
    private Client clientId;

    public PhoneDTO() {
    }

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.clientId = phone.getClientId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }


}
