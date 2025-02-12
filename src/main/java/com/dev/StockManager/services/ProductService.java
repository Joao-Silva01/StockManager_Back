package com.dev.StockManager.services;

import com.dev.StockManager.converter.ProductConverter;
import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.ProductStock;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.repositories.CategoryRepository;
import com.dev.StockManager.repositories.ProductRepository;
import com.dev.StockManager.repositories.ProductStockRepository;
import com.dev.StockManager.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> findAll() {

        return productRepository.findAllQuantity();
    }

    public ProductDTO findById(Integer id) {
        return productRepository.findProduct(id)
                .orElseThrow(() -> new IdNotFoundException("Product not found"));
    }

    @Transactional
    public void Create(ProductDTO productDTO) {

        // Validações
        ProductValidator.validator(productDTO);
        Category category = categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new IdNotFoundException("Category not found!"));

        Product product = ProductConverter.toEntity(productDTO, category);

        productRepository.save(product);

        ProductStock productStock = new ProductStock(productDTO.getQuantity(), product);

        productStockRepository.save(productStock);

    }

    @Transactional
    public void update(Integer id, ProductDTO productDTO) {

        ProductValidator.validatorUpdate(productDTO);
        Product pr = productRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Product not found"));


        Category category = null;

        if (productDTO.getCategory() != null) {
            category = categoryRepository.findById(productDTO.getCategory())
                    .orElseThrow(() -> new IdNotFoundException("Category not found!"));
        }

        Product pr1 = ProductConverter.toEntityUpdate(pr, productDTO, category);

        productRepository.save(pr1);

    }

}
