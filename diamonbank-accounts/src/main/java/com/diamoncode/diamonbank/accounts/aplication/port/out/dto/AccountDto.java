package com.diamoncode.diamonbank.accounts.aplication.port.out.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AccountDto {

    private final long id;

    private final long customerId;

    private final String number;

    private final String type;

    private final BigDecimal balance;

    private final LocalDateTime createDt;

}
