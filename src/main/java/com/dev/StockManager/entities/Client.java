package com.dev.StockManager.entities;


import com.dev.StockManager.entities.enums.UserRole;
import com.dev.StockManager.entities.enums.TypeClient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Client  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String document;
    private String email;
    private Timestamp register_Moment;

    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private TypeClient type;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "clientId",cascade = CascadeType.REMOVE)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private List<SalesOrder> salesOrders = new ArrayList<>();

    public Client(){}

    public Client(Integer id, String name, String document, String email, Timestamp register_Moment, String password, TypeClient type,UserRole role) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.register_Moment = register_Moment;
        this.password = password;
        this.type = type;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<SalesOrder> getSalesOrders() {
        return salesOrders;
    }

    public void setSalesOrders(List<SalesOrder> salesOrders) {
        this.salesOrders = salesOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
