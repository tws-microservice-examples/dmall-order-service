package com.dmall.order.domain.model.query;

public enum OrderStatus {
    NOT_COMPLETED,
    COMPLETED,
    Cancled;

    public static OrderStatus getByOrderEvent(OrderEvent orderEvent) {
        if(OrderEvent.Values.CREATED.name().equals(orderEvent.getName())){
            return NOT_COMPLETED;
        }
        return Cancled;
    }
}
