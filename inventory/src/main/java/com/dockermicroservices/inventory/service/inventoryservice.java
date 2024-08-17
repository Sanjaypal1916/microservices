package com.dockermicroservices.inventory.service;

import org.springframework.stereotype.Service;

import com.dockermicroservices.inventory.repo.inventoryrepo;

@Service
public class inventoryservice {

	
	private final inventoryrepo repo;

	public inventoryservice(inventoryrepo repo) {
		super();
		this.repo = repo;
	}
	
	public Boolean isinstock(String skucode, Integer quantity) {
		return repo.existsBySkucodeAndQuantityIsGreaterThanEqual(skucode,quantity);
	}
	
	
}
