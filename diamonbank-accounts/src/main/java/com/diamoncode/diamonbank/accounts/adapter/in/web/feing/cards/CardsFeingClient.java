package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.cards;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CardsDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "cards", fallback = CardsFallback.class)
public interface CardsFeingClient {

    @PostMapping(value = "/cards/myCards")
    List<CardsDto> getCardDetails(@RequestBody CustomerDto customer);
}
