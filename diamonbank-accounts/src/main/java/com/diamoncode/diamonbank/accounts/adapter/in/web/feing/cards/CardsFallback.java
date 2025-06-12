package com.diamoncode.diamonbank.accounts.adapter.in.web.feing.cards;

import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CardsDto;
import com.diamoncode.diamonbank.accounts.aplication.port.out.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class CardsFallback implements CardsFeingClient{
    @Override
    public List<CardsDto> getCardDetails(CustomerDto customer) {
        log.info("Fallback in cards");
        return Collections.emptyList();
    }
}
