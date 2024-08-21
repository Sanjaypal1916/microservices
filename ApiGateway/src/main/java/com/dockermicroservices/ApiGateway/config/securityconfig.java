package com.dockermicroservices.ApiGateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityconfig {

	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception{
		
		
		return httpsecurity.authorizeHttpRequests(authorize -> authorize.anyRequest()
		.authenticated())
		.oauth2ResourceServer(oauth2-> oauth2.jwt(Customizer.withDefaults()))
		.build();
		
		
	}
}
