package com.dev.StockManager.dtos.sales;

import com.dev.StockManager.dtos.product.CreateShortProductAssociationDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.enums.SalesOrderStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class UpdateSalesOrderDTO implements Serializable {

    private Integer deliveryAddress;

    private Integer phone;

    private List<CreateShortProductAssociationDTO> itens;

    public UpdateSalesOrderDTO(){}

    public UpdateSalesOrderDTO(Integer deliveryAddress, Integer phone) {
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
    }

    public Integer getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Integer deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public List<CreateShortProductAssociationDTO> getItens() {
        return itens;
    }

    public void setItens(List<CreateShortProductAssociationDTO> itens) {
        this.itens = itens;
    }
}
