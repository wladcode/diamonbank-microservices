package com.diamoncode.diamonbank.accounts.controller;

import com.diamoncode.diamonbank.accounts.config.AccountsServiceConfig;
import com.diamoncode.diamonbank.accounts.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.model.JpaEntityCustomer;
import com.diamoncode.diamonbank.accounts.controller.dto.PropertiesDto;
import com.diamoncode.diamonbank.accounts.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/accounts")

public class AccountsController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountsServiceConfig accountsServiceConfig;

    @PostMapping("/myAccount")
    public JpaEntityAccount getAccountDetails(@RequestBody JpaEntityCustomer customer){

        JpaEntityAccount accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

        if (accounts != null){
            return accounts;
        }

        return null;

    }

    @GetMapping("/properties")
    public String getProperties () throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion()
        , accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(properties);

    }
}
