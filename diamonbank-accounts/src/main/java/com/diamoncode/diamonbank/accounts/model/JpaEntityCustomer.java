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
