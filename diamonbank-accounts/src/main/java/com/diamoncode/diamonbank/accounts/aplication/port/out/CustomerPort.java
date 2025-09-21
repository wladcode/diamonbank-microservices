package com.diamoncode.diamonbank.accounts.aplication.port.out;

import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerRequest;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerResponse;

public interface CustomerPort {
    CustomerResponse createCustomer(CustomerRequest customerRequest);
}
