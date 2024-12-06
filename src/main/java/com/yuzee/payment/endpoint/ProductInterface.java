package com.yuzee.payment.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.exception.StripeException;

@RequestMapping("/api/v1")
public interface ProductInterface {

	@GetMapping("/getAllProduct")
	public ResponseEntity<?> ListAllProduct(@RequestParam(value = "limit", required = true) long limit)
			throws StripeException;

}
