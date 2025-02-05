package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.exceptions.ValidatorException;

public class PhoneValidator {
    public static void validator(PhoneDTO phone){

        // verificação do numero de telefone
        if (phone.getNumber() == null || phone.getNumber().isBlank()){
            throw new ValidatorException("Number cannot be null or empty!");
        }else if (!phone.getNumber().strip().matches("[0-9]{11}")){
            throw new ValidatorException("The phone number must have 11 digits!");
        }
    }
}
