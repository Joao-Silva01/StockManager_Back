package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.exceptions.ValidatorException;

public class ProductValidator {
    public static void validator(ProductDTO product) {

        // verificação do name
        if (product.getName() == null) {
            throw new ValidatorException("Name cannot be null!");
        } else if (!product.getName().strip().matches("^[a-zA-Z-]{1,100}$") || product.getName().isBlank()) {
            throw new ValidatorException("Name cannot be empty and must contain only letters!!");
        }

        // verificação do description
        if (product.getDescription() == null) {
            throw new ValidatorException("Description cannot be null!!");
        } else if (!product.getDescription().strip().matches("^[a-zA-Z0-9]{1,100}$") || product.getDescription().isBlank()) {
            throw new ValidatorException("Description cannot be empty!!");
        }

        // verificação do price
        if (product.getPrice() == null) {
            throw new ValidatorException("Price cannot be null");
        } else if (product.getPrice() < 0.01) {
            throw new ValidatorException("price cannot be negative or equal to zero");
        }

        // verificação de category
        if (product.getCategory() == null) {
            throw new ValidatorException("Category cannot be null");
        }

        // verificação do quantity
        if (product.getQuantity() == null) {
            throw new ValidatorException("Quantity cannot be null");
        } else if (product.getQuantity() < 1) {
            throw new ValidatorException("Quantity cannot be negative or zero");
        }
    }

    public static void validatorUpdate(ProductDTO product1, ProductDTO product2) {
        if (product1.getName() != null) {
            if (product1.getName().strip().length() > 1) {
                product2.setName(product1.getName());
            } else {
                throw new ValidatorException("The name must have more than 1 character!");
            }
        }
        if (product1.getDescription() != null) {
            if (product1.getDescription().strip().length() > 1) {
                product2.setDescription(product1.getDescription());
            } else {
                throw new ValidatorException("The description must have more than 1 character!");
            }
        }
        if (product1.getPrice() != null) {
            if (product1.getPrice() > 0.01) {
                product2.setPrice(product1.getPrice());
            } else {
                throw new ValidatorException("price cannot be negative or equal to zero");
            }
        }
        if (product1.getQuantity() != null) {
            if (product1.getQuantity() > 0) {
                product2.setQuantity(product1.getQuantity());
            } else {
                throw new ValidatorException("Quantity cannot be negative or zero");
            }
        }
    }
}
