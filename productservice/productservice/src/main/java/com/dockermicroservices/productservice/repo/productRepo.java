package com.dockermicroservices.productservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dockermicroservices.productservice.model.product;

@Repository
public interface productRepo extends MongoRepository<product, String>{

}
