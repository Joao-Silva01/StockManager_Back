package com.dev.StockManager.converter;

import com.dev.StockManager.dtos.sales.CreateSalesOrderDTO;
import com.dev.StockManager.dtos.sales.UpdateSalesOrderDTO;
import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.SalesOrderProduct;
import com.dev.StockManager.entities.compositePk.ProductAndSalesOrderPK;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static List<SalesOrderProduct> toUpdateEntity(UpdateSalesOrderDTO dto, SalesOrder sales, List<Product> products) {
        List<SalesOrderProduct> salesP = sales.getProducts();

        if (dto.getItens() != null) {
            for (int i = 0; i < sales.getProducts().size(); i++) {
                for (int j = 0; j < dto.getItens().size(); j++) {

                    if (sales.getProducts().get(i).getProduct().getId() == dto.getItens().get(j).getId()) {
                        salesP.set(i, new SalesOrderProduct(sales, products.get(j), dto.getItens().get(j).getQuantity()));
                    }
                }
            }


            List<Product> prod = new ArrayList<>(products);


            for (int i = 0; i < sales.getProducts().size(); i++) {
                for (int j = 0; j < dto.getItens().size(); j++) {

                    var d = sales.getProducts().get(i).getProduct().getId();
                    var w =dto.getItens().get(j).getId();

                    if (sales.getProducts().get(i).getProduct().getId() == dto.getItens().get(j).getId()) {
                        prod.remove(products.get(j));
                    }
                }
            }

            for (int i = 0; i < products.size(); i++) {
                for (int j = 0; j < prod.size(); j++) {


                    if (prod.get(j).getId() == dto.getItens().get(i).getId()) {
                        salesP.add(new SalesOrderProduct(sales, prod.get(j), dto.getItens().get(i).getQuantity()));
                    }
                }
            }
        }

        return salesP;
    }
}
