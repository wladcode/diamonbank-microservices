package com.diamoncode.diamonbank.loans.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@ToString
public class JpaEntityLoans {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_number")
    private long loanNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Column(name = "create_date")
    private String createDate;
}
