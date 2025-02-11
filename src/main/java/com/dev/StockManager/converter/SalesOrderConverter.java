package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.dtos.sales.UpdateSalesOrderDTO;
import com.dev.StockManager.entities.*;
import com.dev.StockManager.exceptions.IdNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SalesOrderConverter {

    public static SalesOrder toCreateEntity(CreateSalesOrderDTO dto, Address address, Phone phone, Client client, List<Product> products) {
        SalesOrder so = new SalesOrder();
        so.setId(dto.getId());
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
        so.calculateTotalPrice();


        return so;
    }

    public static SalesOrder toUpdateEntity(SalesOrder order, UpdateSalesOrderDTO dto) {
        // Posso mudar o endereço de entrega, telefone e o itens


        if (dto.getDeliveryAddress() != null) {
            if (dto.getDeliveryAddress() > order.getClientId().getAddresses().size() - 1) {
                throw new IdNotFoundException("Address not found");
            }
            for (int i = 0; i < order.getClientId().getAddresses().size(); i++) {
                if (dto.getDeliveryAddress() == i) {
                    order.setDeliveryAddress(order.getClientId().getAddresses().get(dto.getDeliveryAddress()));
                }
            }
        }
        if (dto.getPhone() != null) {

            if (dto.getPhone() > order.getClientId().getPhones().size() - 1) {
                throw new IdNotFoundException("Phone not found");
            }

            for (int i = 0; i < order.getClientId().getPhones().size(); i++) {
                if (dto.getPhone() == i) {
                    order.setPhone(order.getClientId().getPhones().get(dto.getPhone()));
                }
            }
        }

        return order;
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
