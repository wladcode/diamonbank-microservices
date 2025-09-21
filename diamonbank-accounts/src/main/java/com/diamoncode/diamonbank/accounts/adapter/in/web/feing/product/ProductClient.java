package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.product;

import com.diamoncode.diamonbank.accounts.adapter.in.web.feing.GenericFallbackFactory;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service", fallbackFactory = ProductClient.ProductFallbackFactory.class)
@CircuitBreaker(name = "product-service", fallbackMethod = "fallback")
@Retry(name = "product-service", fallbackMethod = "fallback")
public interface ProductClient {
    // Define methods to interact with the product service here
    // For example:
    @GetMapping(value = "/api/v1/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);

    // Add other endpoints as needed

    // Fallback method
    default ProductDto fallback(Long id, Exception e) {
        return null; // or return a default ProductDto
    }

    // Fallback factory
    @Component
    class ProductFallbackFactory extends GenericFallbackFactory<ProductClient> {
        public ProductFallbackFactory() {
            super(ProductClient.class);
        }
    }
}
