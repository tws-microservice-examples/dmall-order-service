package com.dmall.order.service;

import com.dmall.order.service.dto.InventoryLockEventDTO;
import com.dmall.order.service.model.Inventory;
import com.dmall.order.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryServiceHystrixClientFallback implements InventoryService {

    @Override
    public ResponseEntity<Inventory> lockInventory(String sku, InventoryLockEventDTO inventoryLockEventDTO) {
        return null;
    }

    @Override
    public Inventory getInventoryBySku(String sku) {
        throw new RuntimeException("get inventory failed");
//        return null;
    }

}
