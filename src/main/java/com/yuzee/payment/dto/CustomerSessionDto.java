package com.yuzee.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.model.CustomerSession.Components;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerSessionDto {

    @JsonProperty("object")
    private String object;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("components")
    private Components components;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("expires_at")
    private long expiresAt;

    @JsonProperty("livemode")
    private boolean livemode;


}
