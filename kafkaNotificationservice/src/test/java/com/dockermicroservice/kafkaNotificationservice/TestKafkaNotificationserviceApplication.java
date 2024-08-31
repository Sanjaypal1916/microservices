package com.dockermicroservice.kafkaNotificationservice;

import org.springframework.boot.SpringApplication;

public class TestKafkaNotificationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(KafkaNotificationserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
