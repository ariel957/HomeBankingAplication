package com.mindhub.HomeBancking.services;

import com.mindhub.HomeBancking.DTO.CardDTO;
import com.mindhub.HomeBancking.models.Card;

import java.util.Set;

public interface CardService {

    Set<CardDTO> getCardDTO();
    void saveCard(Card card);

    Card getCard(long id);
}
