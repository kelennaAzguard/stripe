package com.payment.stripe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import com.payment.stripe.service.CustomerService;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.yuzee.common.lib.dto.stripe.CustomerDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(classes = StripePaymentApplication.class)
@TestInstance(Lifecycle.PER_CLASS)

class StripePaymentApplicationTests {
	

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private CustomerService customerService;


	@Test
	void contextLoads() {
	}

	@Test
	void testCreateCustomerEndpointSuccessfully() throws StripeException {
		// Prepare mock data
		String name = "John Doe";
		String email = "john.doe@example.com";
		Customer customer = new Customer();
		customer.setId("cus_test123");
		customer.setName(name);
		customer.setEmail(email);

		when(customerService.createCustomer(eq(name), eq(email))).thenReturn(customer);

		// Call the endpoint
		ResponseEntity<String> response = restTemplate.exchange("/api/v1/createCustomer?name=" + name + "&email=" + email,
				HttpMethod.POST, null, String.class);

		// Assert response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testCreateCustomerEndpointWithMissingParameters() {
		// Call the endpoint with missing name or email
		ResponseEntity<?> response = restTemplate.exchange("/api/v1/createCustomer?name=&email=", HttpMethod.POST, null,
				String.class);

		// Assert response
		assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.OK);
	}

	@Test
	void testUpdateCustomerEndpointSuccessfully() throws StripeException {
		// Prepare mock data
		String customerId = "cus_test123";
		String orderId = "order_456";
		Customer customerDto = new Customer();
		customerDto.setId(customerId);

		when(customerService.updateCustomer(eq(customerId), eq(orderId))).thenReturn(customerDto);

		// Call the endpoint
		ResponseEntity<?> response = restTemplate.exchange(
				"/api/v1/update-customer?customer_id=" + customerId + "&order_id=" + orderId, HttpMethod.PUT, null,
				String.class);

		// Assert response
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testUpdateCustomerEndpointWithMissingParameters() {
	    // Call the endpoint with missing customerId or orderId
	    ResponseEntity<?> response = restTemplate.exchange(
	            "/api/v1/update-customer?customer_id=&order_id=",
	            HttpMethod.PUT, null, String.class);

	    // Assert response
	    assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.OK);
	}

	@Test
	void testDeleteCustomerEndpointSuccessfully() throws StripeException {
	    // Prepare mock data
	    String customerId = "cus_test123";
	    Customer customerDto = new Customer();
	    customerDto.setId(customerId);

	    when(customerService.deleteCustomer(eq(customerId))).thenReturn(customerDto);

	    // Call the endpoint
	    ResponseEntity<?> response = restTemplate.exchange(
	            "/api/v1/delete-customer/customerId/" + customerId,
	            HttpMethod.DELETE, null, String.class);

	    // Assert response
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testDeleteCustomerEndpointWithMissingCustomerId() {
	    // Call the endpoint with missing customerId
	    ResponseEntity<?> response = restTemplate.exchange(
	            "/api/v1/delete-customer/customerId/",
	            HttpMethod.DELETE, null, String.class);

	    // Assert response
	    assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.OK);
	}

	@Test
	void testRetrieveCustomerEndpointSuccessfully() throws StripeException {
	    // Prepare mock data
	    String customerId = "cus_test123";
	    Customer customerDto = new Customer();
	    customerDto.setId(customerId);

	    when(customerService.retrieveCustomer(eq(customerId))).thenReturn(customerDto);

	    // Call the endpoint
	    ResponseEntity<?> response = restTemplate.exchange(
	            "/api/v1/retrive-customer/customerId/" + customerId,
	            HttpMethod.GET, null, String.class);

	    // Assert response
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testRetrieveCustomerEndpointWithMissingCustomerId() {
	    // Call the endpoint with missing customerId
	    ResponseEntity<?> response = restTemplate.exchange(
	            "/api/v1/retrieve-customer/customerId/",
	            HttpMethod.GET, null, String.class);

	    // Assert response
	    assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.OK);
	}

}
