package com.yuzee.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PortalSessionDto {

	@JsonProperty("url")
	private String url;
}
