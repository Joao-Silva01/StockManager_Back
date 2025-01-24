package com.dev.StockManager.controllers;

import com.dev.StockManager.entities.Client;
import com.dev.StockManager.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> findAll (){
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client){
        clientService.Create(client);
        return ResponseEntity.ok().build();
    }
}
