package com.dev.StockManager.controllers;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.services.ClientService;
import com.dev.StockManager.services.SalesOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SalesOrderService salesService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll (){
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable int id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO client){
        clientService.create(client);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @RequestBody ClientDTO client){
        clientService.update(id,client);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{clientId}/phones/{phoneId}")
    public ResponseEntity<PhoneDTO> updatePhone(@PathVariable Integer clientId, @PathVariable Integer phoneId,@Valid @RequestBody PhoneDTO phone){
        clientService.updateClientPhone(clientId,phoneId,phone);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{clientId}/addresses/{indexAddress}")
    public ResponseEntity<PhoneDTO> updateAddress(@PathVariable Integer clientId, @PathVariable Integer indexAddress,@Valid @RequestBody AddressDTO address){
        clientService.updateClientAddress(clientId,indexAddress,address);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{clientId}/orders")
    public ResponseEntity<List<SalesOrderDTO>> find (@PathVariable Integer clientId){
        return ResponseEntity.ok().body(salesService.allCustomerOrders(clientId));
    }
}
