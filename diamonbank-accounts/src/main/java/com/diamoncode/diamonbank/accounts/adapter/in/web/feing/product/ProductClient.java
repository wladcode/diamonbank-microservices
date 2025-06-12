package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.product;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@Slf4j
class ProductClientFallback implements ProductClient {
    @Override
    public ProductDto getProductById(Long id) {
        log.error("Product not found");
        return null;
    }
}

@FeignClient(value = "product-service", fallback = ProductClientFallback.class)
public interface ProductClient {
    // Define methods to interact with the product service here
    // For example:
     @GetMapping(value = "/api/v1/products/{id}")
     ProductDto getProductById(@PathVariable("id") Long id);

}
