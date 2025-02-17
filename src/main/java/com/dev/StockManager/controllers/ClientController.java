package com.dev.StockManager.controllers;

import com.dev.StockManager.config.security.TokenService;
import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.ResponseDTO;
import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.dtos.client.CreateClientDTO;
import com.dev.StockManager.dtos.client.SingInClientDTO;
import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.services.ClientService;
import com.dev.StockManager.services.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SalesOrderService salesService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @GetMapping(value = "/{clientId}/orders")
    public ResponseEntity<List<SalesOrderDTO>> findAllOrders(@PathVariable Integer clientId) {
        return ResponseEntity.ok().body(salesService.allCustomerOrders(clientId));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> signIn(@RequestBody SingInClientDTO client) {
        String token = clientService.singInClient(client);
        return ResponseEntity.ok().body(new ResponseDTO(client.getName(), token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> create(@RequestBody CreateClientDTO client) {
        String token = clientService.registerClient(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client).toUri();
        return ResponseEntity.created(uri).body(new ResponseDTO(client.getName(), token));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @RequestBody ClientDTO client) {
        clientService.update(id, client);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{clientId}/phones/{phoneId}")
    public ResponseEntity<PhoneDTO> updatePhone(@PathVariable Integer clientId, @PathVariable Integer phoneId, @RequestBody PhoneDTO phone) {
        clientService.updateClientPhone(clientId, phoneId, phone);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{clientId}/addresses/{indexAddress}")
    public ResponseEntity<PhoneDTO> updateAddress(@PathVariable Integer clientId, @PathVariable Integer indexAddress, @RequestBody AddressDTO address) {
        clientService.updateClientAddress(clientId, indexAddress, address);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
