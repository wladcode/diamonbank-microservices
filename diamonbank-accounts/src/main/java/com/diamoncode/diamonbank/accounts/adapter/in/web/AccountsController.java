package com.diamoncode.diamonbank.accounts.adapter.in.web;

import com.diamoncode.diamonbank.accounts.adapter.in.web.config.AccountsServiceConfig;
import com.diamoncode.diamonbank.accounts.aplication.port.in.AccountsUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.PropertiesDto;
import com.diamoncode.i18n.client.factory.I18nFactory;
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

    @GetMapping("/testI18n/{withDefault}")
    public ResponseDTO testI18n(@PathVariable("withDefault") boolean withDefault) {
        String headerName = null;
        if (withDefault) {
            headerName = I18nFactory.getInstance().getMessage("header.name", "es", "Valor por defecto");
        } else {
            headerName = I18nFactory.getInstance().getMessage("header.name", "es");
        }

        return getResponse("Respuesta obtenida desde I18N", headerName);

    }

    @GetMapping("/properties")
    public ResponseDTO getProperties() throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion()
                , accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());

        return getResponse("", properties);

    }


}
