package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Category;
import com.dev.StockManager.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDTO implements Serializable {

    @NotNull
    private Integer code;
    @NotBlank
    private String name;
    private List<Product> products = new ArrayList<>();

    public CategoryDTO(){}

    public CategoryDTO(Category entity) {
        this.code = entity.getCode();
        this.name = entity.getName();
        this.products = entity.getProducts();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
