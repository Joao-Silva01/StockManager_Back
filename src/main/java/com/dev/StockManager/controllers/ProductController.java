package com.dev.StockManager.controllers;


import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping // ALL
    public ResponseEntity<List<?>> findAll() {
        var list = productService.findAll();

        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}") // ALL
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        var entity = productService.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping // ADMIN
    public ResponseEntity<?> create(@RequestBody ProductDTO entity) {
        productService.Create(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}") // ADMIN
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody ProductDTO entity) {
        productService.update(id, entity);
        return ResponseEntity.noContent().build();
    }


}
