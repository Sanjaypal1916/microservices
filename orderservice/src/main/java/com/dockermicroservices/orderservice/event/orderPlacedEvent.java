package com.dockermicroservices.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class orderPlacedEvent {

	private String ordernumber;
	private String email;
	private String firstname;
	private String lastname;
	
}
