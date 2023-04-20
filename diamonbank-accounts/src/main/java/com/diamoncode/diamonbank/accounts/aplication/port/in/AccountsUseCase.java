package com.diamoncode.diamonbank.accounts.aplication.port.in;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;

import java.util.List;

public interface AccountsUseCase {

    List<AccountDto> getAccountsByUser(Long userId);

    AccountDto findById(long idAccount);
}
