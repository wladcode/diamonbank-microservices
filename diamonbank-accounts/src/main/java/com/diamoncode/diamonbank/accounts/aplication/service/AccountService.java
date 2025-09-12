package com.diamoncode.diamonbank.accounts.aplication.service;

import com.diamoncode.diamonbank.accounts.aplication.port.out.AccountPort;
import com.diamoncode.diamonbank.accounts.aplication.port.in.AccountsUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;
import com.diamondcode.common.application.service.UseCase;
import lombok.RequiredArgsConstructor;

import jakarta.transaction.Transactional;
import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AccountService implements AccountsUseCase {

    private final AccountPort accountPort;

    @Override
    public List<AccountDto> getAccountsByUser(Long userId) {
        return accountPort.getAccountsByUser(userId);
    }

    @Override
    public AccountDto findById(long idAccount) {
        return accountPort.getAccountByUser(idAccount);
    }
}
