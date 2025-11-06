package com.diamoncode.diamonbank.cards.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
public class JpaEntityCards {

    @Id
    @Column(name = "card_id")
    private long cardId;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int availableAmount;

    @Column(name = "create_date")
    private Date createDate;

}
