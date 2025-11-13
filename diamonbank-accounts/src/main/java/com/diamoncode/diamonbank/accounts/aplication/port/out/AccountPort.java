package com.diamoncode.diamonbank.accounts.aplication.port.out;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;
import com.diamoncode.diamonbank.accounts.domain.Account;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ConsolidatePositionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountPort {

    List<AccountDto> getAccountsByUser(Long userId);

    ConsolidatePositionDto getConsolidatePosition(Long accountId, String correlationId);

    Account loadAccount(Long accountId, LocalDateTime now);

    AccountDto getAccountByUser(long idAccount);
}
