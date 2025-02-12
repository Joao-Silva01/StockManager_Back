package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.exceptions.ValidatorException;

import java.util.List;

public class PhoneValidator {

    public static void phonesValidator(List<PhoneDTO> phones) {
        // verificação dos numeros de telefones
        for (PhoneDTO phone : phones) {
            //Verifica se o número é nulo ou vazio
            if (phone.getNumber() == null || phone.getNumber().isBlank()) {
                throw new ValidatorException("Number cannot be null or empty!");
            } else if (!phone.getNumber().strip().matches("[0-9]{11}")) {
                // Verifica se só contém números com um tamanho fixo
                throw new ValidatorException("The phone number must have 11 digits!");
            }
        }
    }

    public static void phoneValidator(PhoneDTO phone) {
        // verificação do numero de telefone
        if (phone.getNumber() == null || phone.getNumber().isBlank()) {
            //Verifica se o número é nulo ou vazio
            throw new ValidatorException("Number cannot be null or empty!");
        } else if (!phone.getNumber().strip().matches("[0-9]{11}")) {
            // Verifica se só contém números com um tamanho fixo
            throw new ValidatorException("The phone number must have 11 digits!");
        }

    }

    public static void fixedSizeValidator(Integer index) {
        if (index < 0 || index > 1) {
            throw new ValidatorException("Phone not found");
        }
    }
}
