package com.hatrongtan99.saga.constants;

public class QueueName {
    public static final String CHANGE_STATUS_IN_STOCK_QUEUE_NAME = "product.changeStatusInStock";

    public static final String ORDER_TO_SAGA_UPDATE_QUANTITY_PRODUCT_IN_STOCK_QUEUE_NAME = "order.updateQuantityProductInStock";

    public static final String SAGA_TO_INVENTORY_UPDATE_QUANTITY_PRODUCT_IN_STOCK_QUEUE_NAME = "inventories.updateQuantityProductInStock";
}
