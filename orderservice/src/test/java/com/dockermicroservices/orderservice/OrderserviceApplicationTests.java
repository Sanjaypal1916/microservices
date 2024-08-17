package com.dockermicroservices.orderservice;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) //random port
class OrderserviceApplicationTests {
	
	

	@ServiceConnection
	static MySQLContainer mysqldbcontainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mysqldbcontainer.start();
	}

	@Test
	void shouldcreateproduct() {
		String requestBody = """
												{
				    "skucode":"iphone 15",
				    "price":"150000",
				    "quantity":"150"
				}
												""";
		
		inventorycleintsstub.stubinventorycall("iphone_15", 1);
		
		var response =RestAssured.given()
		.contentType("application.json")
		.body(requestBody)
		.when()
		.post("/api/order")
		.then()
		.log().all()
		.statusCode(201)
		.extract()
		.body()
		.toString();
		
		assertThat(response, Matchers.is("order placed successfully"));

	}

}
