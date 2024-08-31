package com.dockermicroservices.orderservice.serviceImple;

import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dockermicroservices.orderservice.client.inventoryclients;
import com.dockermicroservices.orderservice.event.orderPlacedEvent;
import com.dockermicroservices.orderservice.model.order;
import com.dockermicroservices.orderservice.repository.orderRepository;
import com.dockermicroservices.orderservice.service.orderService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class orderServiceImple implements orderService {
	@Autowired
	private final inventoryclients client;

	@Autowired
	private final orderRepository repo;

	@Autowired
	private final KafkaTemplate<String, orderPlacedEvent> kafka;

	@Override
	public order placeOrder(order o1) {
		org.slf4j.Logger log = LoggerFactory.getLogger(orderServiceImple.class);
		
		
		var isproductinstock = client.isInStock(o1.getSkucode(), o1.getQuantity());

		if (isproductinstock) {

			order object = new order().builder().ordernumber(UUID.randomUUID().toString()).skucode(o1.getSkucode())
					.price(o1.getPrice()).quantity(o1.getQuantity()).build();
			repo.save(object);

//		send message through kafka 
			orderPlacedEvent orderplacedevent = new orderPlacedEvent(object.getOrdernumber(), object.getUserdetail().email(), object.getUserdetail().firstname(), object.getUserdetail().lastname());
			log.info("start - sending orderplaced event");
			kafka.send("message: order-placed", orderplacedevent);
			log.info("sent the msgg to the customer");
			
			return object;
		} else {
			throw new RuntimeException("product with skucode" + o1.getSkucode() + "is not in stock");
		}
	}

}
