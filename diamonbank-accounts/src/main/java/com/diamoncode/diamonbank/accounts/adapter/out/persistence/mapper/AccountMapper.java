package com.diamoncode.diamonbank.accounts.adapter.out.persistence.mapper;

import com.diamoncode.diamonbank.accounts.adapter.out.persistence.model.JpaEntityAccount;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public List<AccountDto> mapToDomain(List<JpaEntityAccount> jpaEntityAccount) {

        if (jpaEntityAccount != null && !jpaEntityAccount.isEmpty()) {

            List<AccountDto> accounts = jpaEntityAccount.stream().map(item -> mapToDomain(item)).collect(Collectors.toList());


            return accounts;

        }

        return Collections.emptyList();
    }

    public AccountDto mapToDomain(JpaEntityAccount item) {

        return new AccountDto(item.getAccountId(), item.getCustomerId(), item.getAccountNumber(), item.getAccountType(), null, item.getCreateDate(), item.getBranchAddress());

    }

    public JpaEntityAccount mapToJpaEntity(AccountDto accountDto) {
        long accountId = 1000000000L + (long) (Math.random() * 9000000000L); // Generate a random account ID

        JpaEntityAccount jpaEntityAccount = new JpaEntityAccount();
        jpaEntityAccount.setAccountId(accountId);
        jpaEntityAccount.setAccountNumber(accountDto.getNumber());
        jpaEntityAccount.setAccountType(accountDto.getType());
        jpaEntityAccount.setCreateDate(LocalDateTime.now());
        jpaEntityAccount.setCustomerId(accountDto.getCustomerId());
        jpaEntityAccount.setBranchAddress(accountDto.getBranchAddress());
        // Assuming there are no additional fields to set, otherwise set them here

        return jpaEntityAccount;

    }
}
