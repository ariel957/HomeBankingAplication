package com.mindhub.HomeBancking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Client {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private long id;

        private String firstName, lastName, mail , password;

        @OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
        private Set<Account> accounts = new HashSet<>();

        @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
        private Set<ClientLoan> clientLoans = new HashSet<>();

        @OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
        private Set<Card> cards = new HashSet<>();

        public Client (){}


        public Client (String firstN , String lastN , String mail, String password){

                this.firstName = firstN;
                this.lastName = lastN;
                this.mail = mail;
                this.password = password;

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

        public Set<Account> getAccounts() {
                return accounts;
        }

        public void addAccount(Account account) {
                account.setOwner(this);
                accounts.add(account);
        }

        public Set<ClientLoan> getClientLoan() {
                return clientLoans;
        }

        public void addClientLoan(ClientLoan clientLoan) {
                clientLoan.setClient(this);
                clientLoans.add(clientLoan);
        }

        public List<Loan> getLoan() {
                return clientLoans.stream().map(clientLoan -> clientLoan.getLoan()).collect(toList());
        }

        public Set<Card> getCards() {
                return cards;
        }

        public void setCards(Set<Card> cards) {
                this.cards = cards;
        }
        public String fullName(){
                return firstName +" "+ lastName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}



