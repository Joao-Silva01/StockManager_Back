package com.dev.StockManager.repositories;

import com.dev.StockManager.dtos.ProductDTO;
import com.dev.StockManager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT p.*, ps.quantity FROM PRODUCT AS P\n" +
            "RIGHT JOIN PRODUCT_STOCK  AS PS\n" +
            "ON p.id = ps.product_id", nativeQuery = true)
    List<ProductDTO> findAllQuantity();

    @Query(value = "SELECT p.*, ps.quantity FROM PRODUCT AS P\n" +
            "RIGHT JOIN PRODUCT_STOCK  AS PS\n" +
            "ON p.id = ps.product_id\n" +
            "WHERE p.id = ?;", nativeQuery = true)
    ProductDTO findProduct(Integer id);
}
