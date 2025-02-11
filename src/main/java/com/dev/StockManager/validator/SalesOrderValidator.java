package com.dev.StockManager.validator;

import com.dev.StockManager.dtos.sales.SalesOrderShortDTO;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.SalesOrder;
import com.dev.StockManager.entities.enums.SalesOrderStatus;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.exceptions.ValidatorException;

public class SalesOrderValidator {

    public static void statusValidator(SalesOrder order) {
        if (order.getStatus() == SalesOrderStatus.CANCELED) {
            throw new ValidatorException("The order cannot be changed as it has already been cancelled.!");
        } else if (order.getStatus() == SalesOrderStatus.COMPLETED) {
            throw new ValidatorException("The order cannot be changed as it has been completed.!");
        }
    }

    public static void createOrderValidator(SalesOrderShortDTO cso, Client client) {
        if (cso.getPhone() == null) {
            throw new ValidatorException("Phone not be null");
        }
        if (cso.getPhone() > client.getPhones().size() - 1 || cso.getPhone() < 0) {
            throw new ValidatorException("Phone not found");
        }
        if (cso.getDeliveryAddress() == null) {
            throw new ValidatorException("Delivery Address not be null");
        }
        if (cso.getDeliveryAddress() > client.getAddresses().size() - 1 || cso.getDeliveryAddress() < 0) {
            throw new ValidatorException("Address not found");
        }
        if (cso.getItens() == null || cso.getItens().isEmpty()) {
            throw new ValidatorException("The product list cannot be null or empty");
        }
    }

    public static void updateOrderValidator(SalesOrderShortDTO cso, Client client) {
        if (cso.getPhone() != null) {
            if (cso.getPhone() > client.getPhones().size() - 1 || cso.getPhone() < 0) {
                throw new ValidatorException("Phone not found");
            }
        }

        if (cso.getDeliveryAddress() != null) {
            if (cso.getDeliveryAddress() > client.getAddresses().size() - 1 || cso.getDeliveryAddress() < 0) {
                throw new ValidatorException("Address not found");
            }
        }
        if (cso.getItens() != null) {
            if (cso.getItens().isEmpty()) {
                throw new ValidatorException("The product list cannot be empty");
            }
        }
    }
}
