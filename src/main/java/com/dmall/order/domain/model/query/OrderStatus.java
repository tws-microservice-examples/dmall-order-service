package com.dmall.order.domain.model.query;

public enum OrderStatus {
    NOT_COMPLETED,
    COMPLETED,
    Cancled;

    public static OrderStatus getByOrderEvent(OrderEventRead orderEventRead) {
        if(OrderEventRead.Values.CREATED.name().equals(orderEventRead.getName())){
            return NOT_COMPLETED;
        }
        return Cancled;
    }
}
