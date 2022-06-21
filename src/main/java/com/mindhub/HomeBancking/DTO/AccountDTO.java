package com.mindhub.HomeBancking.DTO;

import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.models.Client;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AccountDTO {


    private long id;
    private String number;
    private LocalDateTime creationDate;
    private Double balance;

    private Client owner;

    Set<TransactionDTO> transactions = new HashSet<>();


    public AccountDTO (){}


        public AccountDTO (Account account){
            this.id = account.getId();
            this.number = account.getNumber();
            this.creationDate = account.getCreationDate();
            this.balance = account.getBalance();
            this.transactions=account.getTransactions().stream().map(transactions -> new TransactionDTO(transactions)).collect(toSet());
        }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
