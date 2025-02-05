package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.entities.enums.TypeClient;
import com.dev.StockManager.exceptions.ValidatorException;

import java.util.Objects;

public class ClientValidator {
    public static void validator(ClientDTO client) {

        // verificação do name
        if (client.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        } else if (!client.getName().strip().matches("^[a-zA-Z-]{1,100}$") || client.getName().isBlank()) {
            throw new ValidatorException("Name cannot be empty and must contain only letters!!");
        }

        // Verificação do CPF ou CNPJ
        if (client.getCpf_Or_Cnpj() == null || client.getCpf_Or_Cnpj().isBlank()) {
            throw new ValidatorException("cpf or Cnpj cannot be null or empty!");
        } else if (Objects.equals(client.getType().getCode(), TypeClient.INDIVIDUAL_CLIENT.getCode())) {
            if (!CpfOrCnpjValidator.CpfValidator(client.getCpf_Or_Cnpj().strip())) {
                throw new ValidatorException("Incorrect CPF!!");
            }
        } else
        {
            if (!CpfOrCnpjValidator.CnpjValidator(client.getCpf_Or_Cnpj().strip())) {
                throw new ValidatorException("Incorrect CNPJ!!");
            }
        }

        // Verificação do Email
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new ValidatorException("email cannot be null or empty!");
        } else if (!client.getEmail().strip().matches("^[a-zA-Z0-9_-]+@[a-zA-Z]+[.][a-zA-Z]{3}$")) {
            throw new ValidatorException("Incorrect email!!");
        }

        // Verificação do Type
        if(client.getType() == null){
            throw new ValidatorException("Type cannot be null!");
        }

        // Verificação do tamanho maximo de telefones
        if(client.getPhones().size() > 2){
            throw new ValidatorException("Only 2 phones per user!!");
        }

        // Verificação do tamanho maximo de endereços
        if(client.getAddresses().size() > 3){
            throw new ValidatorException("Only 3 addresses per user!!");
        }

    }
}
