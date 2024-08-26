package com.dockermicroservices.ApiGateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

	@Bean
	public RouterFunction<ServerResponse> productServiceRoutes() {

		return GatewayRouterFunctions.route("productservice")
				.route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080")).build();
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
				.route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081")).build();
	}

	@Bean
	public RouterFunction<ServerResponse> InventoryServiceRoutes() {

		return GatewayRouterFunctions.route("inventory")
				.route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http("http://localhost:8082"))
				.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> InventoryServiceSwagger() {

		return GatewayRouterFunctions.route("orderserviceswagger")
				.route(RequestPredicates.path("/aggregate/orderservice/v3/api-docs"),
						HandlerFunctions.http("http://localhost:8081"))
				.filter(setPath("/api-docs")).build();
	}

}