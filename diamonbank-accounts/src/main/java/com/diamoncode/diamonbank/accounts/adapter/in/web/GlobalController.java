package com.diamoncode.diamonbank.accounts.adapter.in.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class GlobalController {

    public static final String ACCOUNTS_REQUEST_MAPPING= "/accounts";
    public static final String ACCOUNTS_REQUEST_MAPPING_GET_BY_ID= "/myAccount/{idAccount}";

}
