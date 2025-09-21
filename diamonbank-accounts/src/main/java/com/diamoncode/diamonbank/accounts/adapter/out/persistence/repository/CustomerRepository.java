package com.diamoncode.diamonbank.accounts.adapter.out.persistence.repository;

import com.diamoncode.diamonbank.accounts.adapter.out.persistence.model.JpaEntityCustomer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<JpaEntityCustomer, Long> {
    Optional<JpaEntityCustomer> findByEmail(String email);
}
