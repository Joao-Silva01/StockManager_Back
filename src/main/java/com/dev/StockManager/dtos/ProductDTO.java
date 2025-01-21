package com.dev.StockManager.dtos;

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

    public ProductDTO(Product entity, ProductStock stock) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.category = entity.getCategory_id().getCode();
        this.quantity = stock.getQuantity();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getCategory() {
        return category;
    }

    public Integer getQuantity() {
        return quantity;
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
