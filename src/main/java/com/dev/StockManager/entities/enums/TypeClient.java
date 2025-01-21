package com.dev.StockManager.entities.enums;

public enum TypeClient {

    INDIVIDUAL_CLIENT(1),
    CORPORATE_CLIENT(2);

    private Integer code;

    TypeClient(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
