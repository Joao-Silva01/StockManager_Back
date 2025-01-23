package com.dev.StockManager.entities;


import com.dev.StockManager.entities.enums.TypeClient;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
public class Client  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    // cpf 11 digitos | cnpj 14 digitos
    private String cpf_Or_Cnpj;
    private String email;
    private Timestamp register_Moment;

    @Enumerated(value = EnumType.STRING)
    private TypeClient type;

    public Client(Integer id, String name, String cpf_Or_Cnpj, String email, Timestamp register_Moment, TypeClient type) {
        this.id = id;
        this.name = name;
        this.cpf_Or_Cnpj = cpf_Or_Cnpj;
        this.email = email;
        this.register_Moment = register_Moment;
        this.type = type;
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
