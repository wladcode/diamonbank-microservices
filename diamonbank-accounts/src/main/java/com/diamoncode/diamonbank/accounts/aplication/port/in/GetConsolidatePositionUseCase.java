package com.diamoncode.diamonbank.accounts.arch.aplication.port.in;

import com.diamoncode.diamonbank.accounts.arch.aplication.port.out.dto.ConsolidatePositionDto;

public interface GetConsolidatePositionUseCase {

    public ConsolidatePositionDto getConsolidatePosition(long customerId);

}
