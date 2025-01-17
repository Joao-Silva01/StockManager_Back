package com.dev.StockManager.repositories;

import com.dev.StockManager.entities.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock,Integer> {
}
