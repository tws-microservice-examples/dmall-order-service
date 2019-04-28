package com.dmall.order.service;

import com.dmall.order.service.dto.InventoryLockEventDTO;
import com.dmall.order.service.model.Inventory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface InventoryService {

    ResponseEntity<Inventory> lockInventory(@PathVariable("sku") final String sku, @RequestBody InventoryLockEventDTO inventoryLockEventDTO);


    Inventory getInventoryBySku(@PathVariable("sku") final String sku);
}
