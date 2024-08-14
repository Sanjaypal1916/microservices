package com.dockermicroservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockermicroservices.orderservice.model.order;

public interface orderRepository extends JpaRepository<order, Long>{

}
