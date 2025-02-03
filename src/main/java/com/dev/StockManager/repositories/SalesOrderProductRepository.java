package com.dev.StockManager.repositories;

import com.dev.StockManager.entities.SalesOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderProductRepository extends JpaRepository<SalesOrderProduct,Integer> {
}
