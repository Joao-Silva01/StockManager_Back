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

public class ClientDTO implements Serializable {

    private Integer id;
    private String name;
    private String cpf_Or_Cnpj;
    private String email;
    private Timestamp register_Moment;
    private TypeClient type;

    private List<PhoneDTO> phones = new ArrayList<>();
    private List<AddressDTO> addresses = new ArrayList<>();

    public ClientDTO() {
    }

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf_Or_Cnpj = entity.getCpf_Or_Cnpj();
        this.email = entity.getEmail();
        this.register_Moment = entity.getRegister_Moment();
        this.type = entity.getType();
        this.phones = entity.getPhones().stream().map(x -> new PhoneDTO(x)).toList();
        this.addresses = entity.getAddresses().stream().map(x -> new AddressDTO(x)).toList();
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

    public String getCpf_Or_Cnpj() {
        return cpf_Or_Cnpj;
    }

    public void setCpf_Or_Cnpj(String cpf_Or_Cnpj) {
        this.cpf_Or_Cnpj = cpf_Or_Cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
