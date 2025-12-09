package com.diamondcode.common.adapter.in.messaging;

public record AccountMessageDto(
        Long accountNumber,
        String name,
        String email,
        String mobileNumber
) {
}
