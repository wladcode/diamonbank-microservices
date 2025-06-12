package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.loans;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.LoansDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class LoansFallback implements  LoansFeingClient {

    @Override
    public List<LoansDto> getLoans(CustomerDto customer) {
        log.info("Fallback in loans");
        return Collections.emptyList();
    }
}