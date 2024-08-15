package com.dockermicroservice.inventory.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockermicroservice.inventory.model.inventory;

public interface inventoryRepository extends JpaRepository<inventory, String>{

	Boolean existsBySkucodeAndQuantityIsGreaterThanEqual(String skucode, BigDecimal quantity);
   
	
}
