package com.dockermicroservice.inventory.model;

import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "inventory_table")
public class inventory {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	
	private String skucode;
	private BigDecimal quantity;
}
