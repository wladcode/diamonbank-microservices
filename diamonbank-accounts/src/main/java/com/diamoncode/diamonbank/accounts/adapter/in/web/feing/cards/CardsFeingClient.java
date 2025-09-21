package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.cards;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CardsDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@FeignClient(value = "cards", fallback = CardsFallback.class)
@CircuitBreaker(name = "cards", fallbackMethod = "getCardDetailsFallback")
@Retry(name = "cards", fallbackMethod = "getCardDetailsFallback")
public interface CardsFeingClient {

    @PostMapping(value = "/cards/myCards")
    List<CardsDto> getCardDetails(@RequestBody CustomerDto customer);

    default List<CardsDto> getCardDetailsFallback(CustomerDto customer, Exception e) {
        return Collections.emptyList();
    }
}
