package com.diamoncode.diamonbank.accounts.adapter.out.persistence.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "communication_sw")
    private Boolean communicationSw;

}
