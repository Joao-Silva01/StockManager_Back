package com.dev.StockManager.dtos;

import com.dev.StockManager.entities.Phone;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.ProductStock;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;
    private String description;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "price cannot be negative or equal to zero")
    private BigDecimal price;

    @NotNull(message = "Category cannot be null")
    private Integer category;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity cannot be negative or zero")
    private Integer quantity;

    public ProductDTO(){}

    public ProductDTO( Integer id,String name,String description,BigDecimal price, Integer category,Integer quantity) {
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

    public @NotBlank(message = "Name cannot be null or empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be null or empty") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull(message = "Price cannot be null") @DecimalMin(value = "0.01", message = "price cannot be negative or equal to zero") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be null") @DecimalMin(value = "0.01", message = "price cannot be negative or equal to zero") BigDecimal price) {
        this.price = price;
    }

    public @NotNull(message = "Category cannot be null") Integer getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "Category cannot be null") Integer category) {
        this.category = category;
    }

    public @NotNull(message = "Quantity cannot be null") @Min(value = 1, message = "Quantity cannot be negative or zero") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull(message = "Quantity cannot be null") @Min(value = 1, message = "Quantity cannot be negative or zero") Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
