package com.diamoncode.diamonbank.accounts.controller.dto;


import java.util.List;

import com.diamoncode.diamonbank.accounts.model.JpaEntityAccount;
import lombok.*;

/**
 * @author EazyBytes
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class CustomerDetailsDto {

    private final JpaEntityAccount accounts;
    private final List<LoansDto> loans;
    private final List<CardsDto> cards;


}