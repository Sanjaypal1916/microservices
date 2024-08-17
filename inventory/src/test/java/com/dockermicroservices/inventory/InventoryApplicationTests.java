package com.dockermicroservices.inventory;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryApplicationTests {

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
				    "skucode":"iphone",
				    "quantity":"100"
				}
												""";
		
		var response =RestAssured.given()
		.contentType("application.json")
		.body(requestBody)
		.when()
		.post("/api/inventory")
		.then()
		.log().all()
		.statusCode(201)
		.extract()
		.body()
		.toString();
		
		assertThat(response, Matchers.is("inventory has the product"));

	}

}
