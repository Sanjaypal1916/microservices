package com.dockermicroservice.kafkaNotificationservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.dockermicroservice.kafkaNotificationservice.event.orderPlacedevent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class notificationService {
	
	@Autowired
	private final JavaMailSender javamailsender;

	
	@KafkaListener(topics = "order-service")
	public void listen(orderPlacedevent orderplaced) {
		log.info("got message from order placed topic");
		
		
		//send email to the consumers
		MimeMessagePreparator mimemessage1 = mimemessage ->{
			MimeMessageHelper messagehelper = new MimeMessageHelper(mimemessage);
			messagehelper.setFrom("springshop@gmail.com");
			messagehelper.setTo(orderplaced.getEmail().toString());
			messagehelper.setSubject(String.format("Your order with the ordernumber {} is placed successfully",orderplaced.getOrdernumber()));
			messagehelper.setText("""
					hi
					
					Your Order with order number {} has been placed successfully
					
					Best Regards,
					Spring Shop.
					""", orderplaced.getOrdernumber());
		};
		
		try {
			
			javamailsender.send(mimemessage1);
			log.info("order placed successfully");
		}catch(Exception e) {
			log.error("",e);
			throw new RuntimeException("exception ocuured when sneding email tp springshop@gmail.com", e);
		}
	}
	

}
