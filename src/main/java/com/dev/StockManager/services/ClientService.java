package com.dev.StockManager.services;

import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.enums.TypeClient;
import com.dev.StockManager.repositories.ClientRepository;
import com.dev.StockManager.validator.CpfOrCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void Create(Client client){

        if(client.getType().getCode() == TypeClient.INDIVIDUAL_CLIENT.getCode() ){
            CpfOrCnpjValidator.CpfValidator(client.getCpf_Or_Cnpj());
        }
        else
        {
            CpfOrCnpjValidator.CnpjValidator(client.getCpf_Or_Cnpj());
        }
        clientRepository.save(client);
    }
}
