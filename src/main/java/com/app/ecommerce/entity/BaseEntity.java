package com.app.ecommerce.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class BaseEntity {

	@CreationTimestamp
	Date craetionTime;
	
	@UpdateTimestamp
	Date timeModified;
}
