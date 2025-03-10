package com.dev.StockManager.controllers;

import com.dev.StockManager.dtos.CategoryDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping // ALL
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok().body(categoryService.ListAll());
    }

    @GetMapping(value = "/{id}") // ALL
    public ResponseEntity<CategoryDTO> findByCategory(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.findbyCategory(id));
    }

    @PostMapping // ADMIN
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO body){
        categoryService.create(body);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(body.getCode()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{code}") // ADMIN
    public ResponseEntity<?> update(@PathVariable Integer code,@RequestBody CategoryDTO body){
        categoryService.update(code,body);
        return ResponseEntity.noContent().build();
    }

}
