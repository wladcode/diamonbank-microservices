package com.diamoncode.diamonbank.accounts.arch.adapter.in.web;

import com.diamoncode.diamonbank.accounts.arch.adapter.in.web.config.AccountsServiceConfig;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.in.AccountsUseCase;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.AccountDto;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.PropertiesDto;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import com.diamondcode.common.adapter.in.web.model.WebAdapterResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING)
@RequiredArgsConstructor
public class AccountsController extends WebAdapterResponse {

    private final AccountsUseCase accountsUseCase;

    private final AccountsServiceConfig accountsServiceConfig;


    @GetMapping("/myAccount/{idAccount}")
    public ResponseDTO getAccountDetails(@PathVariable("idAccount") long idAccount) {
        AccountDto accountDto = accountsUseCase.findById(idAccount);
        return getResponse("", accountDto);

    }

    @GetMapping("/properties")
    public ResponseDTO getProperties() throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion()
                , accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        return getResponse("", properties);

    }


}
