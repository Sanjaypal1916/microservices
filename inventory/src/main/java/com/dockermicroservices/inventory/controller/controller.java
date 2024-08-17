package com.dockermicroservices.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dockermicroservices.inventory.service.inventoryservice;

@RestController
@RequestMapping("/api/inventory")
public class controller {
	private final inventoryservice service;
	
	public controller(inventoryservice service) {
		super();
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Boolean isinstock(@RequestParam String skucode, @RequestParam Integer quantity) {
		return service.isinstock(skucode, quantity);
	}
	
}
