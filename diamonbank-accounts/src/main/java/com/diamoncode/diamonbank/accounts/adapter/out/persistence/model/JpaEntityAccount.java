package com.diamoncode.diamonbank.accounts.arch.adapter.out.persistence.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
public class JpaEntityAccount {

    @Id
    @Column(name = "account_id")
    private long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "created_date")
    private LocalDateTime createDate;

    @Column(name = "customer_id")
    private long customerId;

}
