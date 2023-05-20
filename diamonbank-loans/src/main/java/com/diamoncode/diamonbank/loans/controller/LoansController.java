package com.diamoncode.diamonbank.loans.controller;

import com.diamoncode.diamonbank.loans.config.LoansServiceConfig;
import com.diamoncode.diamonbank.loans.controller.dto.CustomerDto;
import com.diamoncode.diamonbank.loans.model.JpaEntityLoans;
import com.diamoncode.diamonbank.loans.controller.dto.PropertiesDto;
import com.diamoncode.diamonbank.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/loans")
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansServiceConfig loansServiceConfig;


    @PostMapping("/myLoans")
    public List<JpaEntityLoans> getLoansDetails(@RequestBody CustomerDto customer) {
        List<JpaEntityLoans> loans = loansRepository.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());

        if (loans != null) {
            loans.forEach(loan -> log.info("loans data ", loan));

            return loans;
        }

        log.error("Null data");
        return null;
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion()
                , loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        log.info("properties found ", properties.toString());

        return ow.writeValueAsString(properties);

    }
}
