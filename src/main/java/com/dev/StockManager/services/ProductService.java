package com.dev.StockManager.services;

import com.dev.StockManager.dtos.ProductDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.ProductStock;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.repositories.CategoryRepository;
import com.dev.StockManager.repositories.ProductRepository;
import com.dev.StockManager.repositories.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> findAll(){
        return productRepository.findAllQuantity();
    }

    public ProductDTO findById(Integer id){
        return productRepository.findProduct(id)
                .orElseThrow(() -> new IdNotFoundException("Product with Id "+id +" not found"));
    }

    public void Create(ProductDTO productDTO){
        Category c = categoryRepository.findById(productDTO.getCategory()).get();
        Product p1 = new Product(null,productDTO.getName(),productDTO.getDescription(), productDTO.getPrice(), c );

        ProductStock ps1 = new ProductStock(null, productDTO.getQuantity(), p1);

        productRepository.save(p1);
        productStockRepository.save(ps1);
    }


}
