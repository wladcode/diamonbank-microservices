package com.diamoncode.diamonbank.cards.controller;

import com.diamoncode.diamonbank.cards.config.CardsServiceConfig;
import com.diamoncode.diamonbank.cards.controller.dto.CustomerDto;
import com.diamoncode.diamonbank.cards.controller.dto.PropertiesDto;
import com.diamoncode.diamonbank.cards.model.JpaEntityCards;
import com.diamoncode.diamonbank.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")

public class CardsController {
    private static final Logger  logger = LoggerFactory.getLogger(CardsController.class);
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;


    @PostMapping("/myCards")
    public List<JpaEntityCards> getCardDetails(@RequestBody CustomerDto customer) {
        List<JpaEntityCards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            cards.forEach(card -> logger.info("cards data ", card));
            return cards;
        } else {
            logger.error("There is no data found ");
            return null;
        }

    }


    @GetMapping("/properties")
    public String getProperties () throws JsonProcessingException {

        PropertiesDto properties = new PropertiesDto(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion()
                , cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        logger.error("properties found ", properties.toString());

        return ow.writeValueAsString(properties);

    }

}