package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.sales.SalesOrderShortDTO;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.SalesOrderProduct;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SalesOrderProductConverter {

    // Usado para juntar alguns dados específicos e retornar uma lista
    public static List<SalesOrderProduct> toCreateEntity(SalesOrderShortDTO dto, SalesOrder sales, List<Product> products) {
        List<SalesOrderProduct> salesP = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            salesP.add(new SalesOrderProduct(
                    sales,
                    products.get(i),
                    dto.getItens().get(i).getQuantity()
            ));

        }

        return salesP;
    }

    public static List<SalesOrderProduct> toUpdateEntity(SalesOrderShortDTO dto, SalesOrder sales, List<Product> products) {
        List<SalesOrderProduct> salesP = sales.getProducts();

        if (dto.getItens() != null) {

            //Serve para atualizar os itens ja inseridos no pedido
            for (int i = 0; i < sales.getProducts().size(); i++) {
                for (int j = 0; j < dto.getItens().size(); j++) {

                    if (Objects.equals(sales.getProducts().get(i).getProduct().getId(), dto.getItens().get(j).getId())) {
                        salesP.set(i, new SalesOrderProduct(sales, products.get(j), dto.getItens().get(j).getQuantity()));
                    }
                }
            }

            // Serve para deixar na lista prod só os itens novos que vão ser adicionado
            List<Product> prod = products.stream()
                    .filter(s -> sales.getProducts().stream()
                            .noneMatch(z -> z.getProduct().getId() == s.getId()))
                    .toList();


            // Serve para adicionar no final da lista salesP os novos produtos
            for (int i = 0; i < products.size(); i++) {
                for (Product product : prod) {
                    if (Objects.equals(product.getId(), dto.getItens().get(i).getId())) {
                        salesP.add(new SalesOrderProduct(sales, product, dto.getItens().get(i).getQuantity()));
                    }
                }
            }


        }
        return salesP.stream().sorted(Comparator.comparingInt(s-> s.getProduct().getId())).toList();
    }
}
