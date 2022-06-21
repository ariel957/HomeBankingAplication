package com.mindhub.HomeBancking.DTO;

import com.mindhub.HomeBancking.models.Client;
import java.util.HashSet;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

public class ClientDTO {

    private long id;
    private String firstName, lastName, mail;
    private Set<AccountDTO> accountsDTO = new HashSet<>();
    private Set<ClientLoanDTO> loans = new HashSet<>();

    private Set<CardDTO> cards = new HashSet<>();

    public ClientDTO (){}


    public ClientDTO (Client client){
        this.id= client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.mail = client.getMail();
        this.accountsDTO = client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(toSet());
        this.loans = client.getClientLoan().stream().map(clientLoan-> new ClientLoanDTO(clientLoan)).collect(toSet());
        this.cards = client.getCards().stream().filter(card -> card.isActive()).map(card -> new CardDTO(card)).collect(toSet());
    }
    public long getId (){
        return id;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<AccountDTO> getAccounts() {
        return accountsDTO;
    }
    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }
};