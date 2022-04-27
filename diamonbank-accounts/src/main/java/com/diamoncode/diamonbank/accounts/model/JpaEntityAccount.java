package com.diamoncode.diamonbank.accounts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
public class JpaEntityAccount {

    @Id
    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "created_date")
    private LocalDate createDate;

}
