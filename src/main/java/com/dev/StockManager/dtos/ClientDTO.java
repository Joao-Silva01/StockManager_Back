package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Address;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.Phone;
import com.dev.StockManager.entities.enums.TypeClient;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ClientDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "name - cannot be null or empty")
    private String name;

    @NotBlank(message = "cpf_Or_Cnpj - cannot be null or empty")
    private String cpf_Or_Cnpj;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z]+[.][a-zA-Z]{3}$", message = "Incorrect email!!")
    private String email;

    private Timestamp register_Moment;

    @NotNull(message = "type cannot be null")
    private TypeClient type;

    @Size(max = 2, message = "Only 2 phones per user!!")
    private List<PhoneDTO> phones = new ArrayList<>();

    @Size(max = 3, message = "Only 3 addresses per user!!")
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
