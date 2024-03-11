package com.hatrongtan99.saga.constants;

public class RoutingKey {
    public static final String CHANGE_STATUS_IN_STOCK_ROUTE_KEY = "productService.inventoryService.changeStatusInStock.events";
    public static final String ORDER_TO_SAGA_UPDATE_QUANTITY_IN_STOCK_ROUTE_KEY = "orderService.sagaService.updateQuantityInStock.events";
    public static final String SAGA_TO_INVENTORY_UPDATE_QUANTITY_IN_STOCK_ROUTE_KEY = "sagaService.inventoryService.updateQuantityInStock.events";
}
