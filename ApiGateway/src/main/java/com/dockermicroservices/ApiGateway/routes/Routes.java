package com.dockermicroservices.ApiGateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

import java.net.URI;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

	@Bean
	public RouterFunction<ServerResponse> productServiceRoutes() {

		return GatewayRouterFunctions.route("productservice")
				.route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
				.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> productServiceSwagger() {

		return GatewayRouterFunctions.route("productserviceswagger")
				.route(RequestPredicates.path("/aggregate/productservice/v3/api-docs"),
						HandlerFunctions.http("http://localhost:8080"))
				.filter(setPath("/api-docs")).build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> orderServiceSwagger() {

		return GatewayRouterFunctions.route("orderserviceswagger")
				.route(RequestPredicates.path("/aggregate/orderservice/v3/api-docs"),
						HandlerFunctions.http("http://localhost:8081"))
				.filter(setPath("/api-docs")).build();
	}

	@Bean
	public RouterFunction<ServerResponse> orderServiceRoutes() {

		return GatewayRouterFunctions.route("orderservice")
				.route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
				.filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> InventoryServiceRoutes() {

		return GatewayRouterFunctions.route("inventory")
				.route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
				.filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
				.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> InventoryServiceSwagger() {

		return GatewayRouterFunctions.route("orderserviceswagger")
				.route(RequestPredicates.path("/aggregate/orderservice/v3/api-docs"),
						HandlerFunctions.http("http://localhost:8081"))
				.filter(setPath("/api-docs")).build();
	}

//resilince4j fallback routes
	public RouterFunction<ServerResponse> fallbackRoute() {
		return route("fallbackRoute")
				.GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
						.body("service unavailable please try again later"))
				.build();
		
	}
}