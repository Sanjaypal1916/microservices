package com.dockermicroservices.orderservice.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UserDetails(String email, String firstname ,String lastname){
	
	 
}
