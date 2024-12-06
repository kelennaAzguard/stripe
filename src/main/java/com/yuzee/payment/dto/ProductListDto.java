package com.yuzee.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductListDto {
    @JsonProperty("object")
    private String object;

    @JsonProperty("url")
    private String url;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("data")
    private List<ProductDto> data;
}