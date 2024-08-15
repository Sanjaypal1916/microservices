package com.dockermicroservice.inventory.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockermicroservice.inventory.model.inventory;
import com.dockermicroservice.inventory.repository.inventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class inventoryService {
	@Autowired
	private final inventoryRepository repo;
	
	public Boolean isInStocks(String skucode, BigDecimal quantity) {
		return repo.existsBySkucodeAndQuantityIsGreaterThanEqual(skucode, quantity);
		
	}
}
