package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalesOrderConverter {

    public static SalesOrder toCreateEntity(CreateSalesOrderDTO dto, Address address, Phone phone, Client client, List<Product> products) {
        SalesOrder so = new SalesOrder();
        so.setId(dto.getId());
        so.setPriceTotal(dto.getPriceTotal());
        so.setDateMoment(dto.getDateMoment());
        so.setStatus(dto.getStatus());
        so.setDeliveryAddress(address);
        so.setPhone(phone);
        so.setClientId(client);

        // Associa os dados do produto de maneira correta
        List<SalesOrderProduct> sop = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {

            // If usado para colocar a quantidade no produto certo
            if (Objects.equals(dto.getItens().get(i).getId(), products.get(i).getId())) {
                sop.add(new SalesOrderProduct(so, products.get(i), dto.getItens().get(i).getQuantity()));
            }
        }

        so.setProducts(sop);

        return so;
    }

    public static List<ProductDTO> itensOrderConversion(SalesOrder order) {
        return order.getProducts().stream()
                .map(x ->
                        new ProductDTO(x.getProduct().getId(), x.getProduct().getName(),
                                x.getProduct().getDescription(), x.getPrice(),
                                x.getProduct().getCategory_id().getCode(), x.getQuantity()))
                .toList();
    }

}
