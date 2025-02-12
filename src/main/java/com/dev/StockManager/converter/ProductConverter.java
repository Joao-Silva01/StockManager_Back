package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.Category;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.SalesOrder;

import java.util.List;

public class ProductConverter {

    public static Product toEntity(ProductDTO product, Category category) {
        Product pr = new Product();
        pr.setId(product.getId());
        pr.setName(product.getName().strip());
        pr.setDescription(product.getDescription().strip());
        pr.setCategory_id(category);
        pr.setPrice(product.getPrice());
        return pr;
    }

    public static Product toEntityUpdate(Product entity, ProductDTO update, Category category) {


        if (update.getName() != null) {
            if (update.getName().strip().length() > 1) {
                entity.setName(update.getName());
            }
        }
        if (update.getDescription() != null) {
            if (update.getDescription().strip().length() > 1) {
                entity.setDescription(update.getDescription());
            }
        }
        if (update.getPrice() != null) {
            if (update.getPrice().doubleValue() > 0.01) {
                entity.setPrice(update.getPrice());
            }
        }
        if (update.getQuantity() != null) {
            if (update.getQuantity() > 0) {
                entity.getProductStock().setQuantity(update.getQuantity());
            }
        }
        if (category != null) {
            entity.setCategory_id(category);
        }

        return entity;
    }

    public static List<ProductDTO> itensOrderConversion(List<Product> products) {
        return products.stream()
                .map(x ->
                        new ProductDTO(x.getId(),x.getName(),x.getDescription(),x.getPrice(),x.getCategory_id().getCode(),x.getProductStock().getQuantity()))
                .toList();
    }

}
