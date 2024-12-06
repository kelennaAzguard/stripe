package com.yuzee.payment.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import com.stripe.exception.StripeException;
import com.stripe.model.ProductCollection;
import com.yuzee.common.lib.exception.InternalServerException;
import com.yuzee.local.config.MessageTranslator;
import com.yuzee.payment.dto.ProductDto;
import com.yuzee.payment.dto.ProductListDto;
import com.yuzee.payment.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductProcessor {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private MessageTranslator messageTranslator;

	public ProductListDto listOfAllProduct(long limit) throws StripeException {
		log.info("inside customer processor....");
		ProductCollection product = new ProductCollection();
		try {
			product = customerService.listOfAllProduct(limit);
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
		return mapProductCollectionToDto(product);
	}

	private ProductListDto mapProductCollectionToDto(ProductCollection productCollection) {
		ProductListDto productListDto = new ProductListDto();
		productListDto.setObject(productCollection.getObject());
		productListDto.setUrl(productCollection.getUrl());
		productListDto.setHasMore(productCollection.getHasMore());

		List<ProductDto> productDtos = productCollection.getData().stream().map(product -> {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setObject(product.getObject());
			productDto.setActive(product.getActive());
			productDto.setCreated(product.getCreated());
			productDto.setDefaultPrice(product.getDefaultPrice() != null ? product.getDefaultPrice() : null);
			productDto.setDescription(product.getDescription());
			productDto.setImages(product.getImages());
			productDto.setMarketingFeatures(product.getMarketingFeatures());
			productDto.setLivemode(product.getLivemode());
			productDto.setMetadata(product.getMetadata());
			productDto.setName(product.getName());
			productDto.setPackageDimensions(
					product.getPackageDimensions() != null ? product.getPackageDimensions().toString() : null);
			productDto.setShippable(product.getShippable());
			productDto.setStatementDescriptor(product.getStatementDescriptor());
			productDto.setTaxCode(product.getTaxCode());
			productDto.setUnitLabel(product.getUnitLabel());
			productDto.setUpdated(product.getUpdated());
			productDto.setUrl(product.getUrl());
			return productDto;
		}).collect(Collectors.toList());

		productListDto.setData(productDtos);
		return productListDto;
	}

}
