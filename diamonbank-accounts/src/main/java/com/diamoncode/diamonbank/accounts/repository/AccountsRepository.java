package com.diamoncode.diamonbank.accounts.repository;

import com.diamoncode.diamonbank.accounts.model.JpaEntityAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<JpaEntityAccount, Long> {

    JpaEntityAccount findByCustomerId(long customerId);

}
