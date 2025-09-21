package com.diamoncode.diamonbank.accounts.adapter.in.web;

import com.diamoncode.diamonbank.accounts.adapter.in.web.util.ResponseUtil;
import com.diamoncode.diamonbank.accounts.adapter.out.persistence.CustomerAdapter;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerRequest;
import com.diamoncode.diamonbank.accounts.aplication.port.out.request.CustomerResponse;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerAdapter customerAdapter;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerAdapter.createCustomer(customerRequest);
        return ResponseUtil.buildResponseLoad("Customer loaded", customerResponse);
    }
}
