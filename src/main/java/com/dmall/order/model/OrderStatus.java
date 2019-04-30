package com.dmall.order.model;


public enum OrderStatus {
    NOT_COMPLETED,
    COMPLETED,
    UNKOWN_EXCEPTION,
    CANCLED;


    public static OrderStatus getByOrderEvent(OrderEvent orderEventRead) {
        if(OrderEvent.Values.CREATED.name().equals(orderEventRead.getName())){
            return NOT_COMPLETED;
        }
        if(OrderEvent.Values.PAID.name().equals(orderEventRead.getName())){
            return COMPLETED;
        }
        return UNKOWN_EXCEPTION;
    }
}
