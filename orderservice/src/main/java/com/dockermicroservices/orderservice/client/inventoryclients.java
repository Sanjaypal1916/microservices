package com.dockermicroservices.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="inventory", url = "${inventory.url}")
public interface inventoryclients {
	
	@GetMapping("/api/inventory")
	Boolean isinstock(@RequestParam String skucode, @RequestParam Integer quantity);
}
