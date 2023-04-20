package com.diamoncode.diamonbank.accounts.arch.aplication.port.in;


import com.diamoncode.diamonbank.accounts.arch.domain.Money;

/**
 * Read only use case
 * @author wlopez
 *
 */
public interface GetAccountBalanceQueryUseCase {
	Money getAccountBalance(Long accountId);

}
