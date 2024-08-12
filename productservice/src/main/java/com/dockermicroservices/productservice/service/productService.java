package com.dockermicroservices.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dockermicroservices.productservice.dto.productRecord;
import com.dockermicroservices.productservice.dto.productresponse;
import com.dockermicroservices.productservice.model.product;

@Service
public interface productService {
	public product createproduct(productRecord productrecord);
	public product getbyid(String id);
	public List<productresponse> getproduct();
	public void delete(String id);

}
