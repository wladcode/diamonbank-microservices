package com.diamoncode.diamonbank.accounts.adapter.in.web.feing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class GenericFallbackFactory<T> implements FallbackFactory<T> {

    private final Class<T> fallbackClass;

    public GenericFallbackFactory(Class<T> fallbackClass) {
        this.fallbackClass = fallbackClass;
    }

    @Override
    public T create(Throwable cause) {
        log.error("Fallback triggered for service: {}. Error: {}",
                fallbackClass.getSimpleName(), cause.getMessage());

        try {
            return fallbackClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("Failed to create fallback instance for {}", fallbackClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create fallback", e);
        }
    }
}