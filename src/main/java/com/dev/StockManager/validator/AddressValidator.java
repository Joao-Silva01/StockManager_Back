package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.exceptions.ValidatorException;

import java.util.List;

public class AddressValidator {

    public static void addressesValidator(List<AddressDTO> addresses) {

        // Verificação de todos os endereços
        for (AddressDTO address : addresses) {

            // Verificação do nome da rua
            if (address.getStreetName() == null || address.getStreetName().isBlank()) {
                throw new ValidatorException("Street name cannot be null or empty!");
            } else if (!address.getStreetName().strip().matches("^[a-zA-Z]{1,100}$")) {
                throw new ValidatorException("The name must contain only letters!!");
            }

            // Verificação do complemento
            if (address.getComplement().strip().length() > 150 || address.getComplement().strip().length() < 10) {
                throw new ValidatorException("The complement must have between 10 and 150 characters!");
            }

            // Verificação do nome do Bairro
            if (address.getNeighborhoodName() == null || address.getNeighborhoodName().isBlank()) {
                throw new ValidatorException("neighborhoodName - cannot be null or empty!");
            } else if (!address.getNeighborhoodName().strip().matches("^[a-zA-Z]{3,100}$")) {
                throw new ValidatorException("The neighborhood name must be between 10 and 100 characters long!");
            }

            // Verificação do número da casa
            if (address.getNumber() == null) {
                throw new ValidatorException("Number cannot be null!");
            }

            // Verificação do nome do cep da rua
            if (address.getCep() == null || address.getCep().isBlank()) {
                throw new ValidatorException("Cep cannot be null or empty!");
            } else if (!address.getCep().strip().matches("^[0-9]{8}$")) {
                throw new ValidatorException("The postal code can only have numbers and a maximum of 8 digits");
            }
        }
    }

    public static void addressValidator(AddressDTO address) {

        // Verificação do nome da rua
        if (address.getStreetName() == null || address.getStreetName().isBlank()) {
            throw new ValidatorException("Street name cannot be null or empty!");
        } else if (!address.getStreetName().strip().matches("^[a-zA-Z]{1,100}$")) {
            throw new ValidatorException("The name must contain only letters!!");
        }

        // Verificação do complemento
        if (address.getComplement().strip().length() > 150 || address.getComplement().strip().length() < 10) {
            throw new ValidatorException("The complement must have between 10 and 150 characters!");
        }

        // Verificação do nome do Bairro
        if (address.getNeighborhoodName() == null || address.getNeighborhoodName().isBlank()) {
            throw new ValidatorException("neighborhoodName - cannot be null or empty!");
        } else if (!address.getNeighborhoodName().strip().matches("^[a-zA-Z]{3,100}$")) {
            throw new ValidatorException("The neighborhood name must be between 10 and 100 characters long!");
        }

        // Verificação do número da casa
        if (address.getNumber() == null) {
            throw new ValidatorException("Number cannot be null!");
        }

        // Verificação do nome do cep da rua
        if (address.getCep() == null || address.getCep().isBlank()) {
            throw new ValidatorException("Cep cannot be null or empty!");
        } else if (!address.getCep().strip().matches("^[0-9]{8}$")) {
            throw new ValidatorException("The postal code can only have numbers and a maximum of 8 digits");
        }

    }

    public static void fixedSizeValidator(Integer index) {

        if (index < 0 || index > 2) {
            throw new ValidatorException("Address not found");
        }
    }

}

