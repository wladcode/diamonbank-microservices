package com.diamoncode.diamonbank.accounts.adapter.out.persistence;

import com.diamoncode.diamonbank.accounts.adapter.out.persistence.mapper.CustomerMapper;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.model.JpaEntityCustomer;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.repository.CustomerRepository;
import com.diamoncode.diamonbank.accounts.aplication.port.out.CustomerPort;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerRequest;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomerAdapter implements CustomerPort {
    private final CustomerRepository customersRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return customersRepository.findByEmail(customerRequest.email())
                .map(CustomerMapper::mapToResponse)
                .orElseGet(() -> {
                    JpaEntityCustomer newCustomer = CustomerMapper.mapToEntity(customerRequest);
                    JpaEntityCustomer savedCustomer = customersRepository.save(newCustomer);
                    return CustomerMapper.mapToResponse(savedCustomer);
                });
    }
}
