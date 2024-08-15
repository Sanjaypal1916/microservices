package com.dockermicroservice.inventory.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dockermicroservice.inventory.service.inventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class Controller {
	@Autowired
	private inventoryService service;
	
	@GetMapping
	public Boolean isInStock(@RequestParam String skucode, @RequestParam BigDecimal quantity) {
		return service.isInStocks(skucode, quantity);
	}
}
