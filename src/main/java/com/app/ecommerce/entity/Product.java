package com.app.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity{

	@Id
	String productCode;
	String productName;
	int stock;
	double price;
	boolean isActive;
	boolean eligibleForDiscount;
	double discountPercentage;
	String description;	
}
