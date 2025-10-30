package com.wladecode.diamonbank.web.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
public record ConsolidatePositionResponse(
        List<AccounResponse> accounts
        //List<LoansDto> loans;
        //List<CardsDto> cards;
        //ProductDto product;
) {
}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsolidatePositionResponse{

     private List<AccounResponse> accounts;
        //List<LoansDto> loans;
        //List<CardsDto> cards;
        //ProductDto product;

}

