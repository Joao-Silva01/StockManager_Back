package com.dev.StockManager.dtos.product;

public class CreateShortProductAssociationDTO {
    private Integer id;
    private Integer quantity;

    public CreateShortProductAssociationDTO(){}
    public CreateShortProductAssociationDTO(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
