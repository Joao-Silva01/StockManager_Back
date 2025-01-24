package com.dev.StockManager.validator;

import com.dev.StockManager.exceptions.Cpf_Or_CnpjException;
import org.hibernate.validator.constraints.br.CPF;

public class CpfOrCnpjValidator {

    public static boolean CpfValidator(String cpf) {

        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            throw new Cpf_Or_CnpjException("CPF invalid!! "+ cpf);
        }

        int CheckDigit1 = CheckDigit(cpf, 10, "cpf");
        int CheckDigit2 = CheckDigit(cpf, 11, "cpf");

        return CheckDigit1 == Integer.parseInt(String.valueOf(cpf.charAt(9)))
                && CheckDigit2 == Integer.parseInt(String.valueOf(cpf.charAt(10)));
    }

    public static boolean CnpjValidator(String cnpj){

        if(cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d{14}")){
            throw new Cpf_Or_CnpjException("CNPJ invalid!! "+ cnpj);
        }

        int CheckDigit1 = CheckDigit(cnpj, 12,"cnpj");
        int CheckDigit2 = CheckDigit(cnpj,13,"cnpj");

        return CheckDigit1 == Integer.parseInt(String.valueOf(cnpj.charAt(12)))
                && CheckDigit2 == Integer.parseInt(String.valueOf(cnpj.charAt(13)));
    }

    public static int CheckDigit(String person, int quantity, String type) {
        int soma = 0;

        if (type.equalsIgnoreCase("cpf")) {
            int value = quantity;

            for (int i = 0; i < quantity - 1; i++) {
                soma = soma + Integer.parseInt(String.valueOf(person.charAt(i))) * value--;
            }
        } else {
            int[] digitsCnpj1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] digitsCnpj2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            if (quantity == 12) {
                for (int i = 0; i < quantity; i++) {
                    soma += Integer.parseInt(String.valueOf(person.charAt(i))) * digitsCnpj1[i];
                }
            } else {
                for (int i = 0; i < quantity; i++) {
                    soma += Integer.parseInt(String.valueOf(person.charAt(i))) * digitsCnpj2[i];
                }
            }
        }

        soma = soma % 11;

        return soma < 2 ? 0 : 11 - soma;
    }


}
