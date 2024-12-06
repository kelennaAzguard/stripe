package com.yuzee.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.yuzee.common.lib.handler.GenericResponseHandlers;
import com.yuzee.local.config.MessageTranslator;
import com.yuzee.payment.endpoint.ProductInterface;
import com.yuzee.payment.processor.ProductProcessor;

@RestController
public class ProductController implements ProductInterface {
	@Autowired
	private ProductProcessor productProcessor;
	@Autowired
	private MessageTranslator messageTranslator;

	@Override
	public ResponseEntity<?> ListAllProduct(long limit) throws StripeException {
		return new GenericResponseHandlers.Builder().setStatus(HttpStatus.OK)
				.setData(productProcessor.listOfAllProduct(limit))
				.setMessage(messageTranslator.toLocale("list.of.product")).create();
	}

}
