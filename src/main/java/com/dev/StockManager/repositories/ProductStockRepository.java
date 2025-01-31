package com.dev.StockManager.repositories;

import com.dev.StockManager.dtos.ProductDTO;
import com.dev.StockManager.entities.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock,Integer> {

    // Faz uma consulta do database para retornar a quantidade em estoque de um determinado produto.
    @Query(value = "SELECT * FROM PRODUCT_STOCK AS PS WHERE PS.PRODUCT_ID = ?", nativeQuery = true)
    ProductStock findProductStock(Integer id);
}
