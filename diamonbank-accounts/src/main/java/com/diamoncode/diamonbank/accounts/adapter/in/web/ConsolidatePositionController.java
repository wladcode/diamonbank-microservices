package com.diamoncode.diamonbank.accounts.adapter.in.web;

import com.diamoncode.diamonbank.accounts.aplication.port.in.GetConsolidatePositionUseCase;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ConsolidatePositionDto;
import com.diamondcode.common.adapter.in.web.model.ResponseDTO;
import com.diamondcode.common.adapter.in.web.model.WebAdapterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GlobalController.ACCOUNTS_REQUEST_MAPPING)
@RequiredArgsConstructor
public class ConsolidatePositionController extends WebAdapterResponse {

    private final GetConsolidatePositionUseCase getConsolidatePosition;

    @GetMapping("/consolidate/{customerId}")
    public ResponseDTO getCustomerDetails(@PathVariable("customerId") Long customerId) {

        ConsolidatePositionDto consolidatePositionDto = getConsolidatePosition.getConsolidatePosition(customerId);

        return getResponse("", consolidatePositionDto);


    }

}
