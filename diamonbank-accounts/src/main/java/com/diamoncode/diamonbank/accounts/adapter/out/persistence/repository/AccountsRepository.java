package com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.repository;

import com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.model.JpaEntityAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<JpaEntityAccount, Long> {

    List<JpaEntityAccount> findByCustomerId (long customerId);
    Optional<JpaEntityAccount> findByAccountId (long idAccount);

}
