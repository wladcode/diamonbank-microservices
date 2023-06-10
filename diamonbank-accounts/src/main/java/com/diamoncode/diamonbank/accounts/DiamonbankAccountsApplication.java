package com.diamoncode.diamonbank.accounts;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
public class DiamonbankAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiamonbankAccountsApplication.class, args);
    }


    @Bean
    public TimedAspect getTimeAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
