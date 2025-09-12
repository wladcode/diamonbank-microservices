package com.diamoncode.diamonbank.accounts.aplication.service;


import com.diamoncode.diamonbank.accounts.aplication.port.in.GetAccountBalanceQueryUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.AccountPort;
import com.diamoncode.diamonbank.accounts.domain.Money;
import com.diamondcode.common.application.service.UseCase;
import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * Example of a read-only Use case implementation
 *
 * @author wlopez
 */
@UseCase
@Transactional
@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQueryUseCase {

    private final AccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(Long accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }


}
