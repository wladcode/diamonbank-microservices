package com.diamoncode.diamonbank.accounts.adapter.out.persistence.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
public class JpaEntityCustomer {

    @Id
    @Column(name = "customer_id")
    private long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "created_date")
    private LocalDate createDate;
}
