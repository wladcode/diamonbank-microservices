package com.diamoncode.diamonbank.accounts.arch.aplication.service;

import com.diamoncode.diamonbank.accounts.arch.aplication.port.in.GetConsolidatePositionUseCase;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.AccountPort;
import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.ConsolidatePositionDto;
import com.diamondcode.common.application.service.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ConsolidatePositionService implements GetConsolidatePositionUseCase {

    private final AccountPort loadAccountPort;

    @Override
    public ConsolidatePositionDto getConsolidatePosition(long customerId) {

        return loadAccountPort.getConsolidatePosition(customerId);

    }
}
