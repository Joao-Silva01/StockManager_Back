package com.dev.StockManager.repositories;

import com.dev.StockManager.entities.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Integer> {

    @Query(value = "SELECT * FROM SALES_ORDER WHERE CLIENT_ID = ?",nativeQuery = true)
    List<SalesOrder> findA(Integer id);
}
