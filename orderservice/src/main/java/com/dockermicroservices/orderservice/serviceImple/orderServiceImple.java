package com.dockermicroservices.orderservice.serviceImple;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockermicroservices.orderservice.model.order;
import com.dockermicroservices.orderservice.repository.orderRepository;
import com.dockermicroservices.orderservice.service.orderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class orderServiceImple implements orderService{
	
	@Autowired
	private final orderRepository repo;
	

	@Override
	public order placeOrder(order o1) {
		order object = new order().builder()
						.ordernumber(UUID.randomUUID().toString())
						.skucode(o1.getSkucode())
						.price(o1.getPrice())
						.quantity(o1.getQuantity())
						.build();
		repo.save(object);
		return object;
	}

}
