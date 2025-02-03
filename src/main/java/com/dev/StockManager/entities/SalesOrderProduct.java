package com.dev.StockManager.entities;

import com.dev.StockManager.entities.compositePk.ProductAndSalesOrderPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Sales_Order_Product")
public class SalesOrderProduct {

    @EmbeddedId
    private ProductAndSalesOrderPK ps_id = new ProductAndSalesOrderPK();

    private Integer quantity;
    private BigDecimal price;

    public SalesOrderProduct() {
    }

    public SalesOrderProduct(Product product, SalesOrder salesOrder, Integer quantity, BigDecimal price) {
        ps_id.setProduct(product);
        ps_id.setSalesOrder(salesOrder);
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return ps_id.getProduct();
    }
    public SalesOrder getSalesOrder() {
        return ps_id.getSalesOrder();
    }

    public ProductAndSalesOrderPK getPs_id() {
        return ps_id;
    }

    public void setPs_id(ProductAndSalesOrderPK ps_id) {
        this.ps_id = ps_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesOrderProduct that = (SalesOrderProduct) o;
        return Objects.equals(ps_id, that.ps_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ps_id);
    }
}
