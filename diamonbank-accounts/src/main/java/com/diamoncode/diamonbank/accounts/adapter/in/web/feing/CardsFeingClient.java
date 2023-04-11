package com.diamoncode.diamonbank.accounts.adapter.in.web.feing;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CardsDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cards")
public interface CardsFeingClient {

    @PostMapping(value = "/cards/myCards")
    List<CardsDto> getCardDetails(@RequestBody CustomerDto customer);
}
