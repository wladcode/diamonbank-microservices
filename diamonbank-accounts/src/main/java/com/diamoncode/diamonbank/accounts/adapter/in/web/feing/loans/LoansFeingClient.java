package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.loans;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.LoansDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "loans", fallback = LoansFallback.class)
@CircuitBreaker(name = "loans", fallbackMethod = "getLoansFallback")
@Retry(name = "loans", fallbackMethod = "getLoansFallback")
public interface LoansFeingClient {

    @PostMapping(value = "/loans/myLoans")
    List<LoansDto> getLoans(@RequestBody CustomerDto customer);
    
    /**
     * Fallback method for getLoans
     */
    default List<LoansDto> getLoansFallback(CustomerDto customer, Exception e) {
        return Collections.emptyList();
    }
}
