package com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CustomerDto {
    private final long customerId;
}
