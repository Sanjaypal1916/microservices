package com.dockermicroservice.kafkaNotificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class orderPlacedevent {
	private  String ordernumber;
	private  String email;
	
}
