package com.mindhub.HomeBancking.services.implement;

import com.mindhub.HomeBancking.DTO.CardDTO;
import com.mindhub.HomeBancking.Repositories.CardRepository;
import com.mindhub.HomeBancking.models.Card;
import com.mindhub.HomeBancking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CardServiceImpl implements CardService {
   @Autowired
    private CardRepository cardRepository;


    @Override
    public Set<CardDTO> getCardDTO() {
        return cardRepository.findAll().stream().map(card-> new CardDTO(card)).collect(Collectors.toSet());
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public Card getCard(long id) {
        return cardRepository.findById(id).orElse(null);
    }
}
