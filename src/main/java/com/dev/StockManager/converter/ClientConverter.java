package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.dtos.client.ClientShortDTO;
import com.dev.StockManager.dtos.client.CreateClientDTO;
import com.dev.StockManager.entities.Client;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Instant;

public class ClientConverter {

    public static ClientShortDTO shortConversion(Client client) {
        ClientShortDTO cs = new ClientShortDTO();
        cs.setId(client.getId());
        cs.setName(client.getName());
        cs.setEmail(client.getEmail());
        cs.setCpf_Or_Cnpj(client.getDocument());
        cs.setType(client.getType());

        return cs;
    }

    public static Client toEntityCreate(CreateClientDTO dto) {
        PasswordEncoder pe = new BCryptPasswordEncoder();

        Client client = new Client();
        client.setEmail(dto.getEmail().strip());
        client.setName(dto.getName().strip());
        client.setDocument(dto.getDocument().strip());
        // TESTANDO O CRIPT
        client.setPassword(pe.encode(dto.getPassword()));
        client.setRegister_Moment(Timestamp.from(Instant.now()));
        client.setType(dto.getType());

        client.setAddresses(AddressConverter.toListDTO(dto.getAddresses(), client));
        client.setPhones(PhoneConverter.toListDTO(dto.getPhones(), client));

        return client;
    }

    public static Client toEntityUpdate(Client client, ClientDTO dto) {
        if (dto.getName() != null) {
            if (dto.getName().strip().matches("^[a-zA-Z-]{1,100}$") || !dto.getName().isBlank()) {
                client.setName(dto.getName().strip());
            }
        }

        if (dto.getEmail() != null) {
            if (dto.getEmail().strip().matches("^[a-zA-Z0-9_-]+@[a-zA-Z]+[.][a-zA-Z]{3}$")) {
                client.setEmail(dto.getEmail().strip());
            }
        }

        if (dto.getDocument() != null) {
            client.setDocument(dto.getDocument());
        }

        return client;
    }
}
