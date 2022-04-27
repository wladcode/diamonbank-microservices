package com.diamoncode.diamonbank.loans.repository;

import com.diamoncode.diamonbank.loans.model.JpaEntityLoans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<JpaEntityLoans, Long> {

    List<JpaEntityLoans> findByCustomerIdOrderByStartDateDesc(Long customerId);

}
