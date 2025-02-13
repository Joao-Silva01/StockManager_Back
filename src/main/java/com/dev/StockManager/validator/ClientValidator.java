package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.enums.TypeClient;
import com.dev.StockManager.exceptions.ValidatorException;

import java.util.List;
import java.util.Objects;

public class ClientValidator {
    public static void validator(ClientDTO client) {

        // verificação do name
        if (client.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        } else if (!client.getName().strip().matches("^[a-zA-Z-\\s]{1,100}$") || client.getName().isBlank()) {
            throw new ValidatorException("Name cannot be empty and must contain only letters!!");
        }

        // Verificação do CPF ou CNPJ
        if (client.getCpf_Or_Cnpj() == null || client.getCpf_Or_Cnpj().isBlank()) {
            throw new ValidatorException("cpf or Cnpj cannot be null or empty!");
        } else if (Objects.equals(client.getType().getCode(), TypeClient.INDIVIDUAL_CLIENT.getCode())) {
            if (!CpfOrCnpjValidator.CpfValidator(client.getCpf_Or_Cnpj().strip())) {
                throw new ValidatorException("Incorrect CPF!!");
            }
        } else {
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
        if (client.getType() == null) {
            throw new ValidatorException("Type cannot be null!");
        }

        // Verificação do tamanho minimo de telefones
        if (client.getPhones().isEmpty()) {
            throw new ValidatorException("User must have at least 1 phone!!");
        }

        // Verificação do tamanho maximo de telefones
        if (client.getPhones().size() > 2) {
            throw new ValidatorException("Only 2 phones per user!!");
        }

        // Verificação do tamanho minimo de endereços
        if (client.getAddresses().isEmpty()) {
            throw new ValidatorException("User must have at least 1 address!!");
        }

        // Verificação do tamanho maximo de endereços
        if (client.getAddresses().size() > 3) {
            throw new ValidatorException("Only 3 addresses per user!!");
        }

    }

    public static void validatorUpdate(ClientDTO c1, ClientDTO c2) {
        if (c2.getName() != null) {
            if (c2.getName().strip().matches("^[a-zA-Z-]{1,100}$") || !c2.getName().isBlank()) {
                c1.setName(c2.getName().strip());
            } else {
                throw new ValidatorException("Name cannot be empty and must contain only letters!!");
            }
        }
        if (c2.getEmail() != null) {
            if (c2.getEmail().strip().matches("^[a-zA-Z0-9_-]+@[a-zA-Z]+[.][a-zA-Z]{3}$")) {
                c1.setEmail(c2.getEmail().strip());
            } else {
                throw new ValidatorException("Incorrect email!!");
            }
        }
        if (c2.getCpf_Or_Cnpj() != null) {
            if (Objects.equals(c1.getType().getCode(), TypeClient.INDIVIDUAL_CLIENT.getCode())) {
                if (!CpfOrCnpjValidator.CpfValidator(c2.getCpf_Or_Cnpj().strip())) {
                    throw new ValidatorException("Incorrect CPF!!");
                }
            } else {
                if (!CpfOrCnpjValidator.CnpjValidator(c2.getCpf_Or_Cnpj().strip())) {
                    throw new ValidatorException("Incorrect CNPJ!!");
                }
            }
            c1.setCpf_Or_Cnpj(c2.getCpf_Or_Cnpj());
        }
    }

    public static void hasThisCpfOrCnpjValidator(List<ClientDTO> clients, ClientDTO client) {
        for (ClientDTO ctDTO : clients) {
            if (ctDTO.getType() == TypeClient.INDIVIDUAL_CLIENT) {
                if (ctDTO.getCpf_Or_Cnpj().equals(client.getCpf_Or_Cnpj().strip())) {
                    throw new ValidatorException("CPF is already registered!");
                }
            }else{
                if (ctDTO.getCpf_Or_Cnpj().equals(client.getCpf_Or_Cnpj().strip())) {
                    throw new ValidatorException("CNPJ is already registered!");
                }
            }
        }
    }
}
