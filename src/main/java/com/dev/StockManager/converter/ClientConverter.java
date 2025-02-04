package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.dtos.client.ClientShortDTO;
import com.dev.StockManager.entities.Client;

public class ClientConverter {

    public static ClientShortDTO shortConversion(Client client) {
        ClientShortDTO cs = new ClientShortDTO();
        cs.setId(client.getId());
        cs.setName(client.getName());
        cs.setEmail(client.getEmail());
        cs.setCpf_Or_Cnpj(client.getCpf_Or_Cnpj());
        cs.setType(client.getType());

        return cs;
    }

    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO(client);
        /*dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setCpf_Or_Cnpj(client.getCpf_Or_Cnpj());
        dto.setAddresses(client.getAddresses());
        dto.setPhones(client.getPhones());
        dto.setRegister_Moment(client.getRegister_Moment());*/
        return dto;
    }

    public static Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setCpf_Or_Cnpj(dto.getCpf_Or_Cnpj());
        client.setRegister_Moment(dto.getRegister_Moment());
        client.setType(client.getType());
        client.setAddresses(AddressConverter.toListDTO(dto.getAddresses()));
        client.setPhones(PhoneConverter.toListDTO(dto.getPhones()));

        return client;
    }
}
