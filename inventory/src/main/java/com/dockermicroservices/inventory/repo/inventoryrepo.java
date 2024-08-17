package com.dockermicroservices.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dockermicroservices.inventory.model.inventory;

@Repository
public interface inventoryrepo extends JpaRepository<inventory, Long>{
Boolean existsBySkucodeAndQuantityIsGreaterThanEqual(String skucode, Integer quantity);

}
