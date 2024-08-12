package com.dockermicroservices.productservice.serviceimple;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockermicroservices.productservice.repo.productRepo;
import com.dockermicroservices.productservice.dto.productRecord;
import com.dockermicroservices.productservice.dto.productresponse;
import com.dockermicroservices.productservice.model.product;
import com.dockermicroservices.productservice.service.productService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class serviceimple implements productService{

	@Autowired
	private final productRepo repos;
	
	
	public serviceimple(productRepo repos) {
		super();
		this.repos = repos;
	}

	@Override
	public product createproduct(productRecord productrecord) {
		product product1 = product.builder()
							.name(productrecord.name())
							.description(productrecord.description())
							.price(productrecord.price())
							.build();
		repos.save(product1);
//		log.info("product created successfully");
		return product1;
	}

	@Override
	public product getbyid(String id) {
		product product1= repos.findById(id).orElseThrow(()->new RuntimeException("product not found"));
		return product1;
	}


	@Override
	public List<productresponse> getproduct() {
		return repos.findAll()
				.stream()
				.map(product -> new productresponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
				.toList();
	}

	@Override
	public void delete(String id) {
		product product1 = repos.findById(id).orElseThrow(()->new RuntimeException("product not found"));
		repos.delete(product1);
	}
	
}
