package com.diamoncode.diamonbank.accounts.adapter.out.persistence.mapper;

import com.diamoncode.diamonbank.accounts.adapter.out.persistence.model.JpaEntityCustomer;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerRequest;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerResponse;

import java.time.LocalDate;
import java.util.UUID;

public class CustomerMapper {

    public static JpaEntityCustomer mapToEntity(CustomerRequest customerRequest) {
        JpaEntityCustomer jpaEntityCustomer = new JpaEntityCustomer();
        jpaEntityCustomer.setCustomerId(Math.abs(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE));
        jpaEntityCustomer.setName(customerRequest.name());
        jpaEntityCustomer.setEmail(customerRequest.email());
        jpaEntityCustomer.setCreateDate(LocalDate.now());

        return jpaEntityCustomer;
    }

    public static CustomerResponse mapToResponse(JpaEntityCustomer jpaEntityCustomer) {
        return new CustomerResponse(
                jpaEntityCustomer.getCustomerId(),
                jpaEntityCustomer.getName(),
                jpaEntityCustomer.getEmail(),
                jpaEntityCustomer.getMobileNumber()
        );
    }
}
