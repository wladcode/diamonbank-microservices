package com.diamoncode.diamonbank.accounts.aplication.port.in;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.ConsolidatePositionDto;

public interface GetConsolidatePositionUseCase {

    public ConsolidatePositionDto getConsolidatePosition(long customerId, String correlationId);

}
