package com.dockermicroservices.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dockermicroservices.orderservice.model.order;
import com.dockermicroservices.orderservice.service.orderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
public class orderController {
	
private final orderService service ;


public orderController(orderService service) {
	super();
	this.service = service;
}


@PostMapping
public order placeOrder(@RequestBody order o1) {

	order placedOrder = service.placeOrder(o1);
	return placedOrder;
}
	
	
}
