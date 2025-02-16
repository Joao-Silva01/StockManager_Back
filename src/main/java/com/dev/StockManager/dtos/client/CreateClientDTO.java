package com.dev.StockManager.dtos.client;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.enums.TypeClient;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CreateClientDTO implements Serializable {

    private Integer id;
    private String name;
    private String document;
    private String email;
    private String password;
    private Timestamp register_Moment;
    private TypeClient type;

    private List<PhoneDTO> phones = new ArrayList<>();
    private List<AddressDTO> addresses = new ArrayList<>();

    public CreateClientDTO() {
    }

    public CreateClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.document = entity.getDocument();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.register_Moment = entity.getRegister_Moment();
        this.type = entity.getType();
        this.phones = entity.getPhones().stream().map(PhoneDTO::new).toList();
        this.addresses = entity.getAddresses().stream().map(AddressDTO::new).toList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegister_Moment() {
        return register_Moment;
    }

    public void setRegister_Moment(Timestamp register_Moment) {
        this.register_Moment = register_Moment;
    }

    public TypeClient getType() {
        return type;
    }

    public void setType(TypeClient type) {
        this.type = type;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> address) {
        this.addresses = address;
    }
}
