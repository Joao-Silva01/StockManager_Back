package com.dev.StockManager.repositories;

import com.dev.StockManager.entities.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Integer> {
}
