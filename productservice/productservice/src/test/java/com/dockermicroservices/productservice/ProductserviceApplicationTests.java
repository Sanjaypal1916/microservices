package com.dockermicroservices.productservice;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductserviceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongodbcontainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongodbcontainer.start();
	}

	@Test
	void shouldcreateproduct() {
		String requestBody = """
								{
				    "name":"iphone 15",
				    "description":"iphone 15 is a smart phone from apple",
				    "price":"150000"
				}
								""";
		RestAssured.given()
		.contentType("application.json")
		.body(requestBody)
		.when()
		.post("/api/product")
		.then()
		.statusCode(201)
		.body("id", Matchers.notNullValue())
		.body("name", Matchers.equalTo("iphone 15"))
		.body("description", Matchers.equalTo("iphone 15 is a smart phone from apple"))
		.body("price", Matchers.equalTo("150000"));

	}

}
