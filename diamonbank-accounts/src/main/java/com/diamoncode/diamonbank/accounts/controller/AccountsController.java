package com.diamoncode.diamonbank.accounts.controller;

import com.diamoncode.diamonbank.accounts.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.model.JpaEntityCustomer;
import com.diamoncode.diamonbank.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @Autowired
    private AccountsRepository accountsRepository;

    @PostMapping("/myAccount")
    public JpaEntityAccount getAccountDetails(@RequestBody JpaEntityCustomer customer){

        JpaEntityAccount accounts = accountsRepository.findByCustomerId(customer.getCustomerId());

        if (accounts != null){
            return accounts;
        }

        return null;

    }
}
