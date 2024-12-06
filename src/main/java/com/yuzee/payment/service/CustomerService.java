package com.yuzee.payment.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSession;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.model.billingportal.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSessionCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.ProductListParams;
import com.stripe.param.billingportal.SessionCreateParams;

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

	public Session potalSession(String customerId, String url) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		SessionCreateParams params = SessionCreateParams.builder().setCustomer(customerId).setReturnUrl(url).build();
		Session session = Session.create(params);
		return session;
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

	public ProductCollection listOfAllProduct(long limit) throws StripeException {
		Stripe.apiKey = stripeApiKey;
		ProductListParams params = ProductListParams.builder().setLimit(limit).build();

		ProductCollection products = Product.list(params);
		return products;
	}
}
