package com.dev.StockManager.repositories;

import com.dev.StockManager.dtos.product.ProductDTO;
import com.dev.StockManager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    // Faz uma consulta no database trazendo todos os Products, juntando algumas informações da tabela
    // 'Product Stock' com 'Product' de acordo com a chave estrangeira.
    @Query(value = "SELECT p.*, ps.quantity FROM PRODUCT AS P\n" +
            "RIGHT JOIN PRODUCT_STOCK  AS PS\n" +
            "ON p.id = ps.product_id", nativeQuery = true)
    List<ProductDTO> findAllQuantity();

    //Faz uma consulta no database trazendo todos os dados de um único produto.
    @Query(value = "SELECT p.*, ps.quantity FROM PRODUCT AS P\n" +
            "RIGHT JOIN PRODUCT_STOCK  AS PS\n" +
            "ON p.id = ps.product_id\n" +
            "WHERE p.id = ?;", nativeQuery = true)
    Optional<ProductDTO> findProduct(Integer id);
}
