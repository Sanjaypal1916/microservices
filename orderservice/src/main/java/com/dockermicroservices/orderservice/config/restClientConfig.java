package com.dockermicroservices.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.dockermicroservices.orderservice.client.inventoryclients;

public class restClientConfig {

	@Value("${inventory.url")
	private String inventoryServiceUrl;

	@Bean
	public inventoryclients inventoryClient() {
		RestClient restclient = RestClient.builder()
				.baseUrl(inventoryServiceUrl)
				.build();
	
		var restClientAdapter = RestClientAdapter.create(restclient);
		var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
		return httpServiceProxyFactory.createClient(inventoryclients.class);
	
	}
	
}
