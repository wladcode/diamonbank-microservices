package com.diamoncode.diamonbank.accounts.aplication.port.out.dto;


import java.util.List;

import lombok.*;

/**
 * @author EazyBytes
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ConsolidatePositionDto {

    private final List<AccountDto> accounts;
    private final List<LoansDto> loans;
    private final List<CardsDto> cards;
    private final ProductDto product;


}