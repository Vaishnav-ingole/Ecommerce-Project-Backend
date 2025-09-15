package com.app.ecommerce.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	String productCode;
	String productName;
	int stock;
	double price;
	boolean isActive;
	boolean eligibleForDiscount;
	double discountPercentage;
	String description;
}
