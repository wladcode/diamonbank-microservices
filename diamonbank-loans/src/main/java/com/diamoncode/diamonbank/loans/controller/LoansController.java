package com.diamoncode.diamonbank.loans.controller;

import com.diamoncode.diamonbank.loans.controller.dto.CustomerDto;
import com.diamoncode.diamonbank.loans.model.JpaEntityLoans;
import com.diamoncode.diamonbank.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @PostMapping("myLoans")
    public List<JpaEntityLoans> getLoansDetails(@RequestBody CustomerDto customer){
        List<JpaEntityLoans> loans = loansRepository.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());

        if(loans != null){
            return loans;
        }

        return null;
    }
}
