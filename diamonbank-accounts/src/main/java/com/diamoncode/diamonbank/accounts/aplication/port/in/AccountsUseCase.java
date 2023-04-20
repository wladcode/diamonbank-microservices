package com.diamoncode.diamonbank.accounts.arch.aplication.port.in;

import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.AccountDto;

import java.util.List;

public interface AccountsUseCase {

    List<AccountDto> getAccountsByUser(Long userId);

    AccountDto findById(long idAccount);
}
