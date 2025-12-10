package com.diamoncode.diamonbank.accounts.adapter.in.web.fuctions;

import com.diamoncode.diamonbank.accounts.aplication.port.out.AccountPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private static final Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication (AccountPort accountPort){
        return accountNumber ->{
            String message =String.format("Updating communication status for the account number: %s" , accountNumber.toString());
            logger.info(message);
            accountPort.updateCommunicationStatus(accountNumber);
        };
    }
}
