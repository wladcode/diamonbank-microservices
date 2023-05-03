package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.loans;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "loans", fallback = LoansFallback.class)
public interface LoansFeingClient {

    @PostMapping(value = "/loans/myLoans")
    List<LoansDto> getLoans(@RequestBody CustomerDto customer);
}
