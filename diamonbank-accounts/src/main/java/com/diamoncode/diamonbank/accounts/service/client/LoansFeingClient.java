package com.diamoncode.diamonbank.accounts.service.client;

import com.diamoncode.diamonbank.accounts.controller.dto.CustomerDto;
import com.diamoncode.diamonbank.accounts.controller.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("loans")
public interface LoansFeingClient {

    @PostMapping(value = "/loans/myLoans")
    List<LoansDto> getCardDetails(@RequestBody CustomerDto customer);
}
