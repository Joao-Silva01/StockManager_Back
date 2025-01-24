package com.dev.StockManager.entities.enums;

public enum TypeClient {

    INDIVIDUAL_CLIENT(0),
    CORPORATE_CLIENT(1);

    private final Integer code;

    TypeClient(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
