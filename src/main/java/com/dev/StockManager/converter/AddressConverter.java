package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.entities.Address;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.SalesOrder;

import java.util.List;

public class AddressConverter {

    public static Address toEntity(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setCep(dto.getCep());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
        address.setStreetName(dto.getStreetName());
        address.setClient(dto.getClient());

        return address;
    }

    public static Address toEntityUpdate(AddressDTO dto, Address addr) {
        Address address = new Address();
        if (addr != null) {
            address.setId(addr.getId());
            address.setStreetName(dto.getStreetName());
            address.setNeighborhoodName(dto.getNeighborhoodName());
            address.setComplement(dto.getComplement());
            address.setCep(dto.getCep());
            address.setNumber(dto.getNumber());
            address.setClient(addr.getClient());
        }else {
            address.setStreetName(dto.getStreetName());
            address.setComplement(dto.getComplement());
            address.setNeighborhoodName(dto.getNeighborhoodName());
            address.setNumber(dto.getNumber());
            address.setCep(dto.getCep());
        }

        return address;
    }

    public static AddressDTO deliveryConversion(SalesOrder order) {
        AddressDTO dto = new AddressDTO(order.getDeliveryAddress());

        return dto;
    }

    public static List<Address> toListDTO(List<AddressDTO> dto, Client client) {
        return dto.stream().map(x ->
                        new Address(x.getId(), x.getStreetName(),
                                x.getComplement(), x.getNeighborhoodName(),
                                x.getNumber(), x.getCep(), client))
                .toList();
    }


}
