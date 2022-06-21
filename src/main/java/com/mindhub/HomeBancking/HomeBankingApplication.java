package com.mindhub.HomeBancking;
import com.mindhub.HomeBancking.Repositories.*;
import com.mindhub.HomeBancking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBancking.models.TransactionType.CREDITO;
import static com.mindhub.HomeBancking.models.TransactionType.DEBITO;



@SpringBootApplication
public class HomeBankingApplication {
	public static void main(String[] args) {
		SpringApplication.run(HomeBankingApplication.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository , AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository  loanRepository, ClientLoanRepository clientLoanRepository,CardRepository cardRepository) {
		return (args) -> {
			// save a couple of customers
			Client client = new Client("Melba", "Morel", "melba@mindhub.com",passwordEncoder.encode("123456"));
			clientRepository.save(client);
			Client client1 = new Client("Melba","Lorenzo","melo@gmail.com",passwordEncoder.encode("password" ));
			clientRepository.save(client1);
			LocalDateTime today = LocalDateTime.now();

			Account account1 = new Account(	"VIN001",LocalDateTime.now(),5000.0,client1);
			accountRepository.save(account1);
			Account account2 = new Account(	"VIN002",today.plusDays(1),7500.0 ,client1);
			accountRepository.save(account2);
			Account account3 = new Account(	"VIN003",today.plusDays(1),1000.0 ,client);
			accountRepository.save(account3);

			Loan Hipotecario = new Loan ("Hipotecario",500000, List.of(12,24,36,48,60));
			Loan Personal = new Loan ("Personal",100000, List.of(6,12,24));
			Loan Automotriz = new Loan ("Automotriz",300000, List.of(6,12,24,36));
			loanRepository.save(Hipotecario);
			loanRepository.save(Personal);
			loanRepository.save(Automotriz);

			Transaction transaction1 = new Transaction(account2,DEBITO,-400.80,"gastos",LocalDateTime.now());
			transactionRepository.save(transaction1);
			Transaction transaction2 = new Transaction(account2,CREDITO,+500,"compras",LocalDateTime.now());
			transactionRepository.save(transaction2);
			Transaction transaction3 = new Transaction(account2,DEBITO,-350,"servicios",LocalDateTime.now());
			transactionRepository.save(transaction3);
			Transaction transaction4 = new Transaction(account2,CREDITO,+500,"compras",LocalDateTime.now());
			transactionRepository.save(transaction4);
			Transaction transaction5 = new Transaction(account1,DEBITO,-928,"varios",LocalDateTime.now());
			transactionRepository.save(transaction5);
			Transaction transaction6 = new Transaction(account1,CREDITO,+4500,"gsstos",LocalDateTime.now());
			transactionRepository.save(transaction6);
			Transaction transaction7 = new Transaction(account1,DEBITO,-181,"alquiler",LocalDateTime.now());
			transactionRepository.save(transaction7);
			Transaction transaction8 = new Transaction(account1,CREDITO,+1000,"varios",LocalDateTime.now());
			transactionRepository.save(transaction8);

			ClientLoan clientLoan1=new ClientLoan (400000D, 60, client1,Hipotecario );
			clientLoanRepository.save(clientLoan1);
			ClientLoan clientLoan2=new ClientLoan (50000D, 12 ,client1, Personal);
			clientLoanRepository.save(clientLoan2);
			ClientLoan clientLoan3=new ClientLoan (100000D, 24 ,client1,Personal);
			clientLoanRepository.save(clientLoan3);
			ClientLoan clientLoan4=new ClientLoan (50000D, 60 ,client1,Automotriz );
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(CardType.DEBITO,CardColor.GOLD,"4540-4456-0545-0874",654,LocalDateTime.now().minusYears(1),LocalDateTime.now(),client1,true);
			cardRepository.save(card1);
			Card card2 = new Card(CardType.CREDITO,CardColor.TITANIUM,"4540-4456-4685-9275",451,LocalDateTime.now().plusYears(5),LocalDateTime.now(),client1,true);
			cardRepository.save(card2);
			Card card3 = new Card(CardType.CREDITO,CardColor.SILVER,"4865-7512-4587-5964",745,LocalDateTime.now().plusYears(5),LocalDateTime.now(),client1,true);
			cardRepository.save(card3);
		};
	}
}
