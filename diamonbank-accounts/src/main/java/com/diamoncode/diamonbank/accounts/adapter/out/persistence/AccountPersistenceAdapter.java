package com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence;

import com.diamoncode.diamonbank.accounts.arch.adapter.in.web.feing.CardsFeingClient;
import com.diamoncode.diamonbank.accounts.arch.adapter.in.web.feing.LoansFeingClient;
import com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.mapper.AccountMapper;
import com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.repository.AccountsRepository;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.AccountPort;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.*;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.*;
import com.diamoncode.diamonbank.accounts.arch.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements AccountPort {

    private final AccountsRepository accountsRepository;


    private final LoansFeingClient loansFeingClient;

    private final CardsFeingClient cardsFeingClient;

    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAccountsByUser(Long userId) {
        List<JpaEntityAccount> accounts = accountsRepository.findByCustomerId(userId);
        return accountMapper.mapToDomain(accounts);
    }

    @Override
    public ConsolidatePositionDto getConsolidatePosition(Long accountId) {
        CustomerDto customerDto = new CustomerDto(accountId);
        List<JpaEntityAccount> accounts = accountsRepository.findByCustomerId(accountId);
        List<LoansDto> loans = loansFeingClient.getLoans(customerDto);
        List<CardsDto> cards = cardsFeingClient.getCardDetails(customerDto);

        return ConsolidatePositionDto.builder()
                .accounts(accountMapper.mapToDomain(accounts))
                .loans(loans)
                .cards(cards)
                .build();
    }

    @Override
    public Account loadAccount(Long accountId, LocalDateTime now) {
        return null;
    }

    @Override
    public AccountDto getAccountByUser(long idAccount) {
        Optional<JpaEntityAccount> account = accountsRepository.findByAccountId(idAccount);

        if (account.isPresent()) {
            return accountMapper.mapToDomain(account.get());
        }
        return null;
    }
}
