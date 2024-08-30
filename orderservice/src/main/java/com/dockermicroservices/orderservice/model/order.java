package com.dockermicroservices.orderservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orderservice")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ordernumber;
	private String skucode;
	private BigDecimal price;
	private Integer quantity;
	@Transient
	private UserDetails userdetail;

	

}
