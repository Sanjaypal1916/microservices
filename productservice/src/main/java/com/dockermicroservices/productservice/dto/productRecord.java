package com.dockermicroservices.productservice.dto;

import java.math.BigDecimal;

import com.dockermicroservices.productservice.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record productRecord(String id, String name, String description, BigDecimal price) {}
