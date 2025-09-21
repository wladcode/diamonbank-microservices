package com.diamoncode.diamonbank.accounts.aplication.port.out.request;

public record CustomerResponse(
        long id,
        String name,
        String email,
        String phone
) {
}
