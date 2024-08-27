package com.dockermicroservices.orderservice.serviceImple;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockermicroservices.orderservice.client.inventoryclients;
import com.dockermicroservices.orderservice.model.order;
import com.dockermicroservices.orderservice.repository.orderRepository;
import com.dockermicroservices.orderservice.service.orderService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class orderServiceImple implements orderService{
	@Autowired
	private final inventoryclients client;
	
	@Autowired
	private final orderRepository repo;

	
	
	@Override
	public order placeOrder(order o1) {
		var isproductinstock=client.isInStock(o1.getSkucode(), o1.getQuantity());
		
		if(isproductinstock) {
		
		order object = new order().builder()
						.ordernumber(UUID.randomUUID().toString())
						.skucode(o1.getSkucode())
						.price(o1.getPrice())
						.quantity(o1.getQuantity())
						.build();
		repo.save(object);
		return object;
		}else {
			throw new RuntimeException("product with skucode" + o1.getSkucode()+ "is not in stock");
		}
	}

}
