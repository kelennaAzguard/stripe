package com.payment.stripe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.payment.stripe.endpoint.CustomerInterface;
import com.payment.stripe.processor.CustomerProcessor;
import com.stripe.exception.StripeException;
import com.yuzee.common.lib.dto.stripe.CustomerDto;
import com.yuzee.common.lib.handler.GenericResponseHandlers;
import com.yuzee.local.config.MessageTranslator;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@NoArgsConstructor
public class CustomerController implements CustomerInterface{
	@Autowired
	private CustomerProcessor customerProcessor;
	@Autowired
	private MessageTranslator messageTranslator;

	@Override
	public ResponseEntity<?> createCustomer(String name , String email) throws StripeException {
		CustomerDto dto = customerProcessor.createCustomer(name , email);
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(dto)
				.setMessage(messageTranslator.toLocale("create.added")).create();
	}

	@Override
	public ResponseEntity<?> updateCustomer(String customerId , String orderId) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.updateCustomer(customerId, orderId))
				.setMessage(messageTranslator.toLocale("update.customer")).create();
	}

	@Override
	public ResponseEntity<?> deleteCustomer(String customerId) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.deleteCustomer(customerId))
				.setMessage(messageTranslator.toLocale("delete.customer")).create();
	}

	@Override
	public ResponseEntity<?> retrieveCustomer(String customerId) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(customerProcessor.retriveCustomer(customerId))
				.setMessage(messageTranslator.toLocale("retrieve.customer")).create();
	}

}
