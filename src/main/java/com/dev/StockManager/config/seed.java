package com.dev.StockManager.config;

import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.enums.Category;
import com.dev.StockManager.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class seed implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product(null,"Feijão","Feijão Preto", new BigDecimal("12.50"), Category.ALIMENTOS_E_BEBIDAS);

        productRepository.save(p1);
    }
}
