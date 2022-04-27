package com.diamoncode.diamonbank.cards.controller;

import com.diamoncode.diamonbank.cards.controller.dto.CustomerDto;
import com.diamoncode.diamonbank.cards.model.JpaEntityCards;
import com.diamoncode.diamonbank.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public List<JpaEntityCards> getCardDetails(@RequestBody CustomerDto customer) {
        List<JpaEntityCards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }

}