package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.config;

import io.github.resilience4j.circuitbreaker.autoconfigure.CircuitBreakerProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class DefaultCircuitBreakerPropertiesConfiguration implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CircuitBreakerProperties) {
            CircuitBreakerProperties circuitBreakerProperties = (CircuitBreakerProperties) bean;
            circuitBreakerProperties.getInstances()
                    .values()
                    .stream()
                    .filter(instanceProperties -> Objects.isNull(instanceProperties.getBaseConfig()))
                    .forEach(instanceProperties -> instanceProperties.setBaseConfig("default"));
        }
        return bean;
    }
}
