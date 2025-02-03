package com.dev.StockManager.dtos.client;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.enums.TypeClient;

// Classe será usada para ser exibida no Pedido(SalesOrder)
public class ClientShortDTO {

    private Integer id;
    private String name;
    private String cpf_Or_Cnpj;
    private String email;
    private TypeClient type;


    public ClientShortDTO() {
    }

    public ClientShortDTO(Integer id, String name, String cpf_Or_Cnpj, String email, TypeClient type) {
        this.id = id;
        this.name = name;
        this.cpf_Or_Cnpj = cpf_Or_Cnpj;
        this.email = email;
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

    public TypeClient getType() {
        return type;
    }

    public void setType(TypeClient type) {
        this.type = type;
    }
}
