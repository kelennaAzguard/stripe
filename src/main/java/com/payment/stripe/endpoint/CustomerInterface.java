package com.payment.stripe.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.stripe.exception.StripeException;

@RequestMapping("/api/v1")
public interface CustomerInterface {

	@PostMapping("/createCustomer")
	public ResponseEntity<?> createCustomer(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email) throws StripeException;

	@PutMapping("/update-customer")
	public ResponseEntity<?> updateCustomer(@RequestParam(value = "customer_id", required = true) String customerId,
			@RequestParam(value = "order_id", required = true) String orderId) throws StripeException;

	@DeleteMapping("/delete-customer/customerId/{customer_id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customer_id") String customerId)
			throws StripeException;

	@GetMapping("/retrive-customer/customerId/{customer_id}")
	public ResponseEntity<?> retrieveCustomer(@PathVariable("customer_id") String customerId)
			throws StripeException;
}
