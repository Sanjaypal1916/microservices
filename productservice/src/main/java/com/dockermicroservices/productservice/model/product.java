package com.dockermicroservices.productservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class product {
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
	
	
}
