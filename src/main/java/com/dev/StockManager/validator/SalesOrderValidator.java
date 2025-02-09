package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.sales.SalesOrderDTO;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.dev.StockManager.exceptions.ValidatorException;

public class SalesOrderValidator {

    public static void validatorStatus(SalesOrder order) {
        if (order.getStatus() == SalesOrderStatus.CANCELED) {
            throw new ValidatorException("The order cannot be changed as it has already been cancelled.!");
        } else if (order.getStatus() == SalesOrderStatus.COMPLETED) {
            throw new ValidatorException("The order cannot be changed as it has been completed.!");
        }
    }
}
