package com.yuzee.payment.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customer")
@Data
@NoArgsConstructor
public class CustomerModel {
	
	private String id;
	
	private String customerId; 
	
	private String userId;

}
