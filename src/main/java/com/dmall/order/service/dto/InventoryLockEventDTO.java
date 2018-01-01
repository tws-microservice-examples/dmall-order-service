package com.dmall.order.service.dto;

import com.dmall.order.domain.model.OrderItem;

public class InventoryLockEventDTO {

    public Integer getQuantity() {
        return quantity;
    }

    private Integer quantity;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public InventoryLockEventDTO(OrderItem orderItem) {
        this.quantity = orderItem.getAmount();
    }
}
