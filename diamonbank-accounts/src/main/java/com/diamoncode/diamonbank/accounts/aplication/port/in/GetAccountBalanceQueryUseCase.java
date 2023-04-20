package com.diamoncode.diamonbank.accounts.aplication.port.in;


import com.diamoncode.diamonbank.accounts.domain.Money;

/**
 * Read only use case
 * @author wlopez
 *
 */
public interface GetAccountBalanceQueryUseCase {
	Money getAccountBalance(Long accountId);

}
