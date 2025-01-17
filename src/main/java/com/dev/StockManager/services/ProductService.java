package com.dev.StockManager.services;

import com.dev.StockManager.dtos.ProductDTO;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.ProductStock;
import com.dev.StockManager.repositories.ProductRepository;
import com.dev.StockManager.repositories.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    public List<ProductDTO> findAll(){
        return productRepository.findAllQuantity();
    }

    public ProductDTO findById(Integer id){
        return productRepository.findProduct(id);
    }


}
