package com.dockermicroservices.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dockermicroservices.productservice.dto.productRecord;
import com.dockermicroservices.productservice.dto.productresponse;
import com.dockermicroservices.productservice.model.product;
import com.dockermicroservices.productservice.service.productService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class productController {
	@Autowired
	private final productService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public product createproduct(@RequestBody productRecord productrecord) {
		product product1= service.createproduct(productrecord);
		System.out.println("data come here");
		return product1;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<productresponse> getproduct(){
		return service.getproduct();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public product getproduct(@PathVariable String id) {
		return service.getbyid(id);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteproduct(@PathVariable String id) {
		service.delete(id);
	}
}
