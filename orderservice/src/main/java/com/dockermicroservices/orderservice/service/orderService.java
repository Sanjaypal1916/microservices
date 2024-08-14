package com.dockermicroservices.orderservice.service;

import org.springframework.stereotype.Service;

import com.dockermicroservices.orderservice.model.order;

@Service
public interface orderService {

	public order placeOrder(order o1);
}
