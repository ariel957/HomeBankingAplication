package com.mindhub.HomeBancking.controler;

import com.mindhub.HomeBancking.DTO.CardDTO;
import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.Repositories.CardRepository;
import com.mindhub.HomeBancking.Repositories.ClientRepository;
import com.mindhub.HomeBancking.models.*;
import com.mindhub.HomeBancking.services.CardService;
import com.mindhub.HomeBancking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.HomeBancking.Utils.Utils.generator;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;
    @PatchMapping(path = "/cards/{id}")
    public ResponseEntity<Object>desactive(@PathVariable long id,Authentication authentication){
        Card card = cardService.getCard(id);
        Client client = clientService.getClientCurrent(authentication);
        if (card == null){
            return new ResponseEntity<>("The card does not exist",HttpStatus.FORBIDDEN);
        }
        if (LocalDateTime.now().isAfter(card.getThruDate())){
            card.getThruDate();
        }
        if (!client.getCards().contains(card)){
            return new ResponseEntity<>("The card does not belong to the client",HttpStatus.FORBIDDEN);
        }
       if (!card.isActive()){
           return new ResponseEntity<>("The card is deactivated",HttpStatus.FORBIDDEN);
       }
       card.setActive(false);
       cardService.saveCard(card);
       return new ResponseEntity<>("The card has been deleted",HttpStatus.ACCEPTED);

    };

    @GetMapping("/cards")
    public Set<CardDTO> getCardDTO() {
        return cardService.getCardDTO();
    }

    @PostMapping(path = "/clients/current/cards")
    public ResponseEntity<Object> createCards(@RequestParam CardType type,@RequestParam  CardColor color,Authentication authentication){
        Client client = clientService.getClientCurrent(authentication);

        Set<Card> cards = client.getCards();
        cards = cards.stream().filter(card -> card.getType()==type).collect(Collectors.toSet());
        if (cards.size() >= 3) {
            return new ResponseEntity<>("No puedes tener mas de 3 tarjetas", HttpStatus.FORBIDDEN);
        }
        Card card = new Card(type,color,generator(1111,9999)+"-"+ generator(1111,9999)+"-"+ generator(1111,9999)+"-"+ generator(1111,9999), generator(100,999),LocalDateTime.now().plusYears(5),LocalDateTime.now(),client,true);
        cardService.saveCard(card);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
