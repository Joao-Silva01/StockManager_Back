package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.entities.Address;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.Phone;
import com.dev.StockManager.entities.SalesOrder;

import java.util.List;

public class PhoneConverter {

    public static Phone toEntity(PhoneDTO dto, Client client){
        Phone phone = new Phone();
        phone.setId(dto.getId());
        phone.setNumber(dto.getNumber());
        phone.setClientId(client);

        return phone;
    }

    public static PhoneDTO phoneSalesConversion(SalesOrder order){
        PhoneDTO dto = new PhoneDTO(order.getPhone());
        return dto;
    }

    public static List<Phone> toListDTO (List<PhoneDTO> dto, Client client){
        return dto.stream().map(x ->
                        new Phone(x.getId(),
                                x.getNumber().strip(),
                                client))
                .toList();
    }
}
