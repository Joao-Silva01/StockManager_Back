package com.dev.StockManager.dtos;

import com.dev.StockManager.converter.ProductConverter;
import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDTO implements Serializable {

    private Integer code;
    private String name;
    private List<ProductDTO> products = new ArrayList<>();

    public CategoryDTO(){}

    public CategoryDTO(Category entity) {
        this.code = entity.getCode();
        this.name = entity.getName();
        this.products = ProductConverter.itensOrderConversion(entity.getProducts());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }


}
