package com.diamoncode.diamonbank.accounts.controller;

import com.diamoncode.diamonbank.accounts.config.AccountsServiceConfig;
import com.diamoncode.diamonbank.accounts.controller.dto.*;
import com.diamoncode.diamonbank.accounts.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.model.JpaEntityCustomer;
import com.diamoncode.diamonbank.accounts.repository.AccountsRepository;
import com.diamoncode.diamonbank.accounts.service.client.CardsFeingClient;
import com.diamoncode.diamonbank.accounts.service.client.LoansFeingClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")

public class AccountsController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountsServiceConfig accountsServiceConfig;

    @Autowired
    private LoansFeingClient loansFeingClient;

    @Autowired
    private CardsFeingClient cardsFeingClient;


    @PostMapping("/myAccount")
    public JpaEntityAccount getAccountDetails(@RequestBody JpaEntityCustomer customer) {

        JpaEntityAccount accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

        if (accounts != null) {
            return accounts;
        }

        return null;

    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion()
                , accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(properties);

    }

    @PostMapping("/myCustomerDetails")
    public CustomerDetailsDto getCustomerDetails(@RequestBody CustomerDto customerDto) {

        JpaEntityAccount accounts = accountsRepository.findByCustomerId(customerDto.getCustomerId());
        List<LoansDto> loans = loansFeingClient.getCardDetails(customerDto);
        List<CardsDto> cards = cardsFeingClient.getCardDetails(customerDto);

        return CustomerDetailsDto.builder()
                .accounts(accounts)
                .loans(loans)
                .cards(cards)
                .build();

    }
}
