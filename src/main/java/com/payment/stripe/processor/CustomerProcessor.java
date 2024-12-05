package com.payment.stripe.processor;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import com.payment.stripe.dto.CustomerSessionDto;
import com.payment.stripe.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSession;
import com.yuzee.common.lib.dto.stripe.CustomerDto;
import com.yuzee.common.lib.exception.InternalServerException;
import com.yuzee.common.lib.exception.NotFoundException;
import com.yuzee.local.config.MessageTranslator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerProcessor {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private MessageTranslator messageTranslator;
	@Autowired
	private ModelMapper modelMapper;

	public CustomerDto createCustomer(String name, String email) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(email) || ObjectUtils.isEmpty(name)) {
			log.info("name or email must not be null");
			throw new NotFoundException(messageTranslator.toLocale("name or email.is null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.createCustomer(name, email);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return mapCustomerToDto(customer);

	}

	public CustomerDto updateCustomer(String customerId, String orderId) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(customerId) || ObjectUtils.isEmpty(orderId)) {
			log.info("customerId or orderId must not be null");
			throw new NotFoundException(messageTranslator.toLocale("customerId or orderId must not be null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.updateCustomer(customerId, orderId);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return mapCustomerToDto(customer);
	}

	public CustomerDto deleteCustomer(String customerId) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(customerId)) {
			log.info("customerId must not be null");
			throw new NotFoundException(messageTranslator.toLocale("customerId.is null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.deleteCustomer(customerId);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return mapCustomerToDto(customer);
	}

	public CustomerDto retriveCustomer(String customerId) throws StripeException {
		log.info("inside customer processor....");
		if (ObjectUtils.isEmpty(customerId)) {
			log.info("customerId must not be null");
			throw new NotFoundException(messageTranslator.toLocale("customerId.is null"));
		}
		Customer customer = new Customer();
		try {
			customer = customerService.retrieveCustomer(customerId);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			// Handle specific HTTP exceptions
			log.error("HTTP error occurred while creating customer: {} - {}", e.getStatusCode(),
					e.getResponseBodyAsString());
			throw new InternalServerException(
					"HTTP error occurred: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		} catch (RestClientException e) {
			// Handle generic RestTemplate exceptions
			log.error("Exception occurred while creating customer: ", e);
			throw new InternalServerException("Exception occurred while creating customer", e);
		}
		return mapCustomerToDto(customer);
	}

	private CustomerDto mapCustomerToDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();

		customerDto.setId(customer.getId());
		customerDto.setObject(customer.getObject());

		if (customer.getAddress() != null) {
			customerDto.setAddress(customer.getAddress());
		}
		if (ObjectUtils.isNotEmpty(customer.getBalance())) {
			customerDto.setBalance(customer.getBalance());
		}
		if (ObjectUtils.isNotEmpty(customer.getCreated())) {
		    customerDto.setCreated(customer.getCreated());
		}
		if (ObjectUtils.isNotEmpty(customer.getCurrency())) {
		    customerDto.setCurrency(customer.getCurrency());
		}
		if (ObjectUtils.isNotEmpty(customer.getDefaultSource())) {
		    customerDto.setDefaultSource(customer.getDefaultSource());
		}
		if (ObjectUtils.isNotEmpty(customer.getDelinquent())) {
		    customerDto.setDelinquent(customer.getDelinquent());
		}
		if (ObjectUtils.isNotEmpty(customer.getDescription())) {
		    customerDto.setDescription(customer.getDescription());
		}
		if (ObjectUtils.isNotEmpty(customer.getDiscount())) {
		    customerDto.setDiscount(customer.getDiscount());
		}
		if (ObjectUtils.isNotEmpty(customer.getEmail())) {
		    customerDto.setEmail(customer.getEmail());
		}
		if (ObjectUtils.isNotEmpty(customer.getInvoicePrefix())) {
		    customerDto.setInvoicePrefix(customer.getInvoicePrefix());
		}

		if (customer.getInvoiceSettings() != null) {
			customerDto.setInvoiceSettings(customer.getInvoiceSettings());
		}

		if (ObjectUtils.isNotEmpty(customer.getLivemode())) {
		    customerDto.setLivemode(customer.getLivemode());
		}
		if (ObjectUtils.isNotEmpty(customer.getMetadata())) {
		    customerDto.setMetadata(customer.getMetadata());
		}
		if (ObjectUtils.isNotEmpty(customer.getName())) {
		    customerDto.setName(customer.getName());
		}
		if (ObjectUtils.isNotEmpty(customer.getNextInvoiceSequence())) {
		    customerDto.setNextInvoiceSequence(customer.getNextInvoiceSequence());
		}
		if (ObjectUtils.isNotEmpty(customer.getPhone())) {
		    customerDto.setPhone(customer.getPhone());
		}
		if (ObjectUtils.isNotEmpty(customer.getPreferredLocales())) {
		    customerDto.setPreferredLocales(customer.getPreferredLocales());
		}
		if (ObjectUtils.isNotEmpty(customer.getShipping())) {
		    customerDto.setShipping(customer.getShipping());
		}
		if (ObjectUtils.isNotEmpty(customer.getTaxExempt())) {
		    customerDto.setTaxExempt(customer.getTaxExempt());
		}
		if (ObjectUtils.isNotEmpty(customer.getTestClock())) {
		    customerDto.setTestClock(customer.getTestClock());
		}


		return customerDto;
	}

}