package com.yuzee.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.model.Product.MarketingFeature;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("created")
    private Long created;

    @JsonProperty("default_price")
    private String defaultPrice;

    @JsonProperty("description")
    private String description;

    @JsonProperty("images")
    private List<String> images;

    @JsonProperty("marketing_features")
    private List<MarketingFeature> marketingFeatures;

    @JsonProperty("livemode")
    private Boolean livemode;

    @JsonProperty("metadata")
    private Map<String, String> metadata;

    @JsonProperty("name")
    private String name;

    @JsonProperty("package_dimensions")
    private String packageDimensions;

    @JsonProperty("shippable")
    private Boolean shippable;

    @JsonProperty("statement_descriptor")
    private String statementDescriptor;

    @JsonProperty("tax_code")
    private String taxCode;

    @JsonProperty("unit_label")
    private String unitLabel;

    @JsonProperty("updated")
    private Long updated;

    @JsonProperty("url")
    private String url;
}