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

    public List<ProductDTO> findAll(){

        return productRepository.findAllQuantity();
    }

    public ProductDTO findById(Integer id){
        return productRepository.findProduct(id)
                .orElseThrow(() -> new IdNotFoundException("Product id not found"));
    }

    @Transactional
    public void Create(ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategory())
                .orElseThrow(() -> new IdNotFoundException("Category not found!"));

        Product product = new Product(null,productDTO.getName().strip(),productDTO.getDescription().strip(),
                productDTO.getPrice(), category );

        productRepository.save(product);

        ProductStock productStock = new ProductStock(productDTO.getQuantity(), product);

        productStockRepository.save(productStock);

    }

    public void update(Integer id, ProductDTO productDTO){
        ProductDTO prDTO = findById(id);

        // Atualizando campos não nulos
        if (productDTO.getName() != null) prDTO.setName(productDTO.getName());
        if (productDTO.getDescription() != null) prDTO.setDescription(productDTO.getDescription());
        if (productDTO.getPrice() != null) prDTO.setPrice(productDTO.getPrice());
        if(productDTO.getQuantity() != null) prDTO.setQuantity(productDTO.getQuantity());

        Product pr = new Product(prDTO.getId(), prDTO.getName(), prDTO.getDescription(),
                prDTO.getPrice(), categoryRepository.findById(prDTO.getCategory()).get());

        ProductStock stock = productStockRepository.findProductStock(prDTO.getId());
        stock.setQuantity(prDTO.getQuantity());

        productRepository.save(pr);
        productStockRepository.save(stock);
    }


}
