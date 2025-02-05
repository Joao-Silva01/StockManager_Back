package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.exceptions.ValidatorException;

public class ProductValidator {
    public static boolean validator(ProductDTO product) {

        // verificação do name
        if (product.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        }

        if (!product.getName().strip().matches("^[a-zA-Z-]{1,100}$") || product.getName().isBlank()) {
            throw new ValidatorException("Name cannot be empty and must contain only letters!!");
        }


        // verificação do description
        if (product.getDescription() == null) {
            throw new ValidatorException("Description cannot be null!!");
        }

        if (!product.getDescription().strip().matches("^[a-zA-Z0-9]{1,100}$") || product.getDescription().isBlank()) {
            throw new ValidatorException("Description cannot be empty!!");
        }

        // verificação do price
        if (product.getPrice() == null) {
            throw new ValidatorException("Price cannot be null");
        }

        if (product.getPrice() < 0.01) {
            throw new ValidatorException("price cannot be negative or equal to zero");
        }


        // verificação de category
        if (product.getCategory() == null) {
            throw new ValidatorException("Category cannot be null");
        }

        // verificação do quantity
        if (product.getQuantity() == null) {
            throw new ValidatorException("Quantity cannot be null");
        }
        if (product.getQuantity() < 1) {
            throw new ValidatorException("Quantity cannot be negative or zero");
        }

        return true;
    }
}
