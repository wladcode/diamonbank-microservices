package com.diamoncode.diamonbank.accounts.aplication.port.out.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private long id;

    private long customerId;

    @NotEmpty(message = "Account number cannot be empty")
    private String number;

    @NotEmpty(message = "Account type cannot be empty")
    private String type;

    @NotNull(message = "Balance cannot be null")
    private BigDecimal balance;

    private LocalDateTime createDt;

    @NotNull(message = "Address cannot be null")
    private String branchAddress;
}
