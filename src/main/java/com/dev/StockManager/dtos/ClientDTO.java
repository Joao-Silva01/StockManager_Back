package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.enums.TypeClient;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ClientDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "name - cannot be null or empty")
    private String name;

    @NotBlank(message = "cpf_Or_Cnpj - cannot be null or empty")
    private String cpf_Or_Cnpj;
    private String email;

    private Timestamp register_Moment;

    @NotNull(message = "type cannot be null")
    private TypeClient type;

    public ClientDTO() {
    }

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf_Or_Cnpj = entity.getCpf_Or_Cnpj();
        this.email = entity.getEmail();
        this.register_Moment = entity.getRegister_Moment();
        this.type = entity.getType();
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

    public @NotNull(message = "type cannot be null") TypeClient getType() {
        return type;
    }

    public void setType(@NotNull(message = "type cannot be null") TypeClient type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
