package com.dmall.order.service;

import com.dmall.order.service.dto.InventoryLockEventDTO;
import com.dmall.order.service.model.Inventory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "inventory-service", fallback = InventoryServiceHystrixClientFallback.class)
public interface InventoryService {

    @RequestMapping(value = "/inventories/{sku}/lockEvents", method = RequestMethod.POST, headers = "Accept=application/json")
    ResponseEntity<Inventory> lockInventory(@PathVariable("sku") final String sku, @RequestBody InventoryLockEventDTO inventoryLockEventDTO);


    @RequestMapping(value = "/inventories/{sku}", method = RequestMethod.GET, headers = "Accept=application/json")
    Inventory getInventoryBySku(@PathVariable("sku") final String sku);
}
