package com.dev.StockManager.entities.compositePk;

import com.dev.StockManager.entities.Product;
import com.dev.StockManager.entities.SalesOrder;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductAndSalesOrderPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAndSalesOrderPK that = (ProductAndSalesOrderPK) o;
        return Objects.equals(product, that.product) && Objects.equals(salesOrder, that.salesOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, salesOrder);
    }
}
