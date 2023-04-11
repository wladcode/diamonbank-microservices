package com.diamoncode.diamonbank.accounts.aplication.port.in;

import com.diamoncode.diamonbank.accounts.adapter.out.persistence.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountsUseCase {

    List<AccountDto> getAccountsByUser(Long userId);

    AccountDto findById(long idAccount);
}
