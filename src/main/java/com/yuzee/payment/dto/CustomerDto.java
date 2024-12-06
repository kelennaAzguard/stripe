package com.yuzee.payment.dto;

import java.util.List;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.model.Customer.InvoiceSettings;
import com.stripe.model.Discount;
import com.stripe.model.ShippingDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("object")
	private String object;

	@JsonProperty("address")
	private AddressDto address;

	@JsonProperty("balance")
	private long balance;

	@JsonProperty("created")
	private long created;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("default_source")
	private String defaultSource;

	@JsonProperty("delinquent")
	private boolean delinquent;

	@JsonProperty("description")
	private String description;

	@JsonProperty("discount")
	private Discount discount;

	@JsonProperty("email")
	private String email;

	@JsonProperty("invoice_prefix")
	private String invoicePrefix;

	@JsonProperty("invoice_settings")
	private InvoiceSettings invoiceSettings;

	@JsonProperty("livemode")
	private boolean livemode;

	@JsonProperty("metadata")
	private Map<String, String> metadata;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("deleted")
	private String deleted;

	@JsonProperty("next_invoice_sequence")
	private Long nextInvoiceSequence;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("preferred_locales")
	private List<String> preferredLocales;

	@JsonProperty("shipping")
	private ShippingDetails shipping;

	@JsonProperty("tax_exempt")
	private String taxExempt;

	@JsonProperty("test_clock")
	private String testClock;

}
