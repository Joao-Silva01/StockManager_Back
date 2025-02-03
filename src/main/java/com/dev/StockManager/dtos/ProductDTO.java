package com.dev.StockManager.dtos;

import jakarta.validation.constraints.*;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "price cannot be negative or equal to zero")
    private Double price;

    @NotNull(message = "Category cannot be null")
    private Integer category;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity cannot be negative or zero")
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, String description, Double price, Integer category, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
