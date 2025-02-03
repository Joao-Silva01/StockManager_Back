package com.dev.StockManager.mapper;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.ClientDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.dtos.client.ClientShortDTO;
import com.dev.StockManager.entities.Client;

import java.util.Optional;

public class ClientMapper {

    public static ClientShortDTO simpleDataConversion(Client client) {
        ClientShortDTO cs = new ClientShortDTO();
        cs.setId(client.getId());
        cs.setName(client.getName());
        cs.setEmail(client.getEmail());
        cs.setCpf_Or_Cnpj(client.getCpf_Or_Cnpj());
        cs.setType(client.getType());

        return cs;
    }


}
