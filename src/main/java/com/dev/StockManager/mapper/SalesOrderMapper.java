package com.dev.StockManager.mapper;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.dtos.ProductDTO;
import com.dev.StockManager.dtos.SalesOrderDTO;
import com.dev.StockManager.entities.Address;
import com.dev.StockManager.entities.Phone;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.SalesOrderProduct;

import java.util.List;

public class SalesOrderMapper {

    public static SalesOrderDTO toDTO (SalesOrder order){
        SalesOrderDTO sales = new SalesOrderDTO(order);
        return sales;
    }

    public static List<ProductDTO> itensConversion(SalesOrder order){
        return order.getProducts().stream()
                .map(x ->
                        new ProductDTO(x.getProduct().getId(), x.getProduct().getName(),
                                x.getProduct().getDescription(), x.getPrice(),
                                x.getProduct().getCategory_id().getCode(), x.getQuantity()))
                .toList();
    }

    public static AddressDTO addressConversion(SalesOrder order){
        AddressDTO dto = new AddressDTO(order.getDeliveryAddress());

        return dto;
    }

    public static PhoneDTO phoneConversion(SalesOrder order){
        PhoneDTO dto = new PhoneDTO(order.getPhone());
        return dto;
    }
}
