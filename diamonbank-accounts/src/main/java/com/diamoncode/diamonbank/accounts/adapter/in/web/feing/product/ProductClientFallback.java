package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.product;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public ProductDto getProductById(Long id) {
        log.warn("Using fallback for getProductById({}) - Product Service is unavailable", id);
        return null; // or return a default ProductDto with some default values
    }
}
