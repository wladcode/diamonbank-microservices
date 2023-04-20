package com.diamoncode.diamonbank.accounts.arch.adapter.in.web;

import com.diamoncode.diamonbank.accounts.arch.aplication.port.in.AccountsUseCase;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.in.GetConsolidatePositionUseCase;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.AccountDto;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.ConsolidatePositionDto;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import com.diamondcode.common.adapter.in.web.model.WebAdapterResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING)
@RequiredArgsConstructor
public class ConsolidatePositionController extends WebAdapterResponse {

    private final GetConsolidatePositionUseCase getConsolidatePosition;
    private final AccountsUseCase accountsUseCase;

    @GetMapping("/consolidate/{customerId}")
    @CircuitBreaker(name = "detailForCustomerSupportApp", fallbackMethod = "")
    public ResponseDTO getCustomerDetails(@PathVariable("customerId") Long customerId) {

        ConsolidatePositionDto consolidatePositionDto = getConsolidatePosition.getConsolidatePosition(customerId);

        return getResponse("", consolidatePositionDto);


    }

    private ResponseDTO myCustomerDetailsFallBack(Long customerId, Throwable throwable) {
        List<AccountDto> accounts = accountsUseCase.getAccountsByUser(customerId);
        ConsolidatePositionDto consolidatePositionDto = ConsolidatePositionDto.builder()
                .accounts(accounts)
                .build();

        return getResponse("", consolidatePositionDto);
    }

}
