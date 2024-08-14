package com.dockermicroservices.orderservice;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.MySQLContainer;

import com.dockermicroservices.productservice.TestcontainersConfiguration;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
		
		RestAssured.given()
		.contentType("application.json")
		.body(requestBody)
		.when()
		.post("/api/order")
		.then()
		.statusCode(201)
		.body("id", Matchers.notNullValue())
		.body("ordernumber", Matchers.notNullValue())
		.body("skucode", Matchers.equalTo("iphone 15"))
		.body("price", Matchers.equalTo("150000"))
		.body("quantity", Matchers.equalTo("150"));

	}

}
