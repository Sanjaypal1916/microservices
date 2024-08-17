package com.dockermicroservices.orderservice;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public class inventorycleintsstub {

	public static void stubinventorycall(String skucode, Integer quantity) {
		stubFor(get(urlEqualTo("api/inventory?skucode" + skucode + " &Quantity " + quantity))
				.willReturn((aResponse())
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("true")));
		
	}
}
