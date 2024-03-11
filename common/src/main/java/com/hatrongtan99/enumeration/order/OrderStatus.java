package com.hatrongtan99.enumeration.order;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    PENDING("pending"),
    PENDING_PAYMENT("pending-payment"),
    PAID("paid"),
    ACCEPTED("accepted"),
    PREPARE("prepare"),
    DELIVERY("delivery"),
    SHIPPING("shipping"),
    COMPLETED("completed"),
    REFUND("refunded"),
    REJECTED("rejected");

    private String name;
    OrderStatus(String name) {
        this.name = name;
    }

    public static OrderStatus getByName(String name) {
        if (name.equals("all")) {
            return null;
        }
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.name.equals(name)) {
                return orderStatus;
            }
        }
        return null;
    }

    public static List<OrderStatus> getListStatusAccept() {
        return Arrays.asList(PAID, ACCEPTED, PREPARE, SHIPPING, COMPLETED,DELIVERY);
    }

    public static List<OrderStatus> getListStatusCompensation() {
        return Arrays.asList(REFUND, REJECTED);
    }

}
