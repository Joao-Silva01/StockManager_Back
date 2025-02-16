package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.dtos.sales.SalesOrderShortDTO;
import com.dev.StockManager.entities.*;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.dev.StockManager.exceptions.IdNotFoundException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SalesOrderConverter {

    public static SalesOrder toCreateEntity(SalesOrderShortDTO dto, Address address, Phone phone, Client client, List<Product> products) {
        SalesOrder so = new SalesOrder();
        so.setDeliveryAddress(address);
        so.setPhone(phone);

        // são associados automaticamente
        so.setClientId(client);
        so.setDateMoment(Timestamp.from(Instant.now()));
        so.setStatus(SalesOrderStatus.PEDING);

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

    public static SalesOrder toUpdateEntity(SalesOrder order, SalesOrderShortDTO dto) {

        if (dto.getDeliveryAddress() != null) {

            // Loop para atualizar o endereço do cliente de acordo com o index dele
            for (int i = 0; i < order.getClientId().getAddresses().size(); i++) {
                if (dto.getDeliveryAddress() == i) {
                    order.setDeliveryAddress(order.getClientId().getAddresses().get(dto.getDeliveryAddress()));
                }
            }
        }
        if (dto.getPhone() != null) {

            // Loop para atualizar o telefone do cliente de acordo com o index dele
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

    public static Address deliveryAddressAssociationConverter(SalesOrderShortDTO order, Client client) {
        Address address = new Address();

        // Loop para retornar/associar o endereço de acordo com index dele na lista getAddresses()
        for (int i = 0; i < client.getAddresses().size(); i++) {
            if (order.getDeliveryAddress() == i) {
                address = client.getAddresses().get(order.getDeliveryAddress());
                break;
            }
        }

        return address;
    }

    public static Phone phoneAssociationConverter(SalesOrderShortDTO salesDTO, Client client) {
        Phone phone = new Phone();

        // Loop para retornar/associar o telefone de acordo com index dele na lista getPhones()
        for (int i = 0; i < client.getPhones().size(); i++) {
            if (salesDTO.getPhone() == i) {
                phone = client.getPhones().get(salesDTO.getPhone());
                break;
            }
        }

        return phone;
    }
}
