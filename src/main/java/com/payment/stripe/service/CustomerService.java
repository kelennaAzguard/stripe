package com.payment.stripe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSession;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSessionCreateParams;
import com.stripe.param.CustomerUpdateParams;

@Service
public class CustomerService {

	@Value("${stripe.api.key}")
	private String stripeApiKey;

	public Customer createCustomer(String name, String email) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		CustomerCreateParams params = CustomerCreateParams.builder().setName(name).setEmail(email).build();
		Customer customer = Customer.create(params);
		return customer;

	}

	public Customer updateCustomer(String customerId, String orderId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		Customer resource = Customer.retrieve(customerId);
		CustomerUpdateParams params = CustomerUpdateParams.builder().putMetadata("order_id", orderId).build();
		Customer customer = resource.update(params);
		return customer;
	}

	public Customer deleteCustomer(String customerId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		Customer resource = Customer.retrieve(customerId);
		return resource.delete();
	}

	public Customer retrieveCustomer(String customerId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		Customer customer = Customer.retrieve(customerId);
		return customer;
	}

	public CustomerSession createCustomerSession(String customerId) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		CustomerSessionCreateParams params = CustomerSessionCreateParams.builder().setCustomer(customerId)
				.setComponents(CustomerSessionCreateParams.Components.builder()
						.setPricingTable(
								CustomerSessionCreateParams.Components.PricingTable.builder().setEnabled(true).build())
						.build())
				.build();
		CustomerSession customerSession = CustomerSession.create(params);
		return customerSession;
	}
}
