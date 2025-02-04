package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.SalesOrderProduct;
import com.dev.StockManager.entities.compositePk.ProductAndSalesOrderPK;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderProductConverter {

    // Usado para juntar alguns dados específicos e retornar uma lista
    public static List<SalesOrderProduct> toCreateEntity(CreateSalesOrderDTO dto, SalesOrder sales, List<Product> products) {
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
}
