package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.CategoryDTO;
import com.dev.StockManager.exceptions.ValidatorException;

public class CategoryValidator {
    public static void validator(CategoryDTO category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new ValidatorException("Name cannot be null or empty!");
        }else if(!category.getName().strip().matches("^[a-zA-Zç\\s]{3,100}$")){
            throw new ValidatorException("The name must contain only letters!!");
        }
    }
}
