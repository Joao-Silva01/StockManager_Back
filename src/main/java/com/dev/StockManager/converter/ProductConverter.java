package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.entities.Product;

public class ProductConverter {

    public static Product toEntity(ProductDTO product, Category category){
        Product pr = new Product();
        pr.setId(product.getId());
        pr.setName(product.getName().strip());
        pr.setDescription(product.getDescription().strip());
        pr.setCategory_id(category);
        pr.setPrice(product.getPrice());
        return pr;
    }

}
