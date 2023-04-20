package com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.mapper;

import com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public List<AccountDto> mapToDomain(List<JpaEntityAccount> jpaEntityAccount) {

        if (jpaEntityAccount != null && !jpaEntityAccount.isEmpty()) {

            List<AccountDto> accounts = jpaEntityAccount.stream().map(item -> new AccountDto(item.getAccountId(),
                    item.getCustomerId(),
                    item.getAccountNumber(),
                    item.getAccountType(),
                    null,
                    item.getCreateDate())).collect(Collectors.toList());


            return accounts;

        }

        return Collections.emptyList();
    }

    public AccountDto mapToDomain(JpaEntityAccount item) {

        return new AccountDto(item.getAccountId(),
                item.getCustomerId(),
                item.getAccountNumber(),
                item.getAccountType(),
                null,
                item.getCreateDate());

    }
}
