package com.dev.StockManager.entities.enums;

public enum SalesOrderStatus {
    PEDING(1),
    COMPLETED(2),
    CANCELED(3);

    private Integer cod;

    SalesOrderStatus(int code) {
        this.cod = code;
    }
}
