package com.mindhub.HomeBancking.controler;

import com.mindhub.HomeBancking.Repositories.AccountRepository;
import com.mindhub.HomeBancking.Repositories.ClientRepository;
import com.mindhub.HomeBancking.Repositories.TransactionRepository;
import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.models.Client;
import com.mindhub.HomeBancking.models.Transaction;
import com.mindhub.HomeBancking.services.AccountService;
import com.mindhub.HomeBancking.services.ClientService;
import com.mindhub.HomeBancking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.mindhub.HomeBancking.models.TransactionType.CREDITO;
import static com.mindhub.HomeBancking.models.TransactionType.DEBITO;

@RestController

@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Transactional
    @PostMapping(path = "/transactions")
    public ResponseEntity<Object> createTransaction(Authentication authentication,
                    @RequestParam Double amount , @RequestParam String description,
                    @RequestParam String originAccountNumber, @RequestParam String targetAccountNumber
    ) {

        Client client = clientService.getClientCurrent(authentication);
        Account originAccount = accountService.findByNumber(originAccountNumber);
        Account targetAccount = accountService.findByNumber(targetAccountNumber);

            //Verificar que los parámetros no estén vacíos//
        if (description.isEmpty() || originAccountNumber.isEmpty() || targetAccountNumber.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }
        //Verificar que los parámetros no estén vacíos (number)//
        if (amount == 0 || amount.isNaN() || amount.isInfinite()) {

            return new ResponseEntity<>("Input value not allowed", HttpStatus.FORBIDDEN);

        }
        //Verificar que exista la cuenta de origen//
        if (originAccount == null) {

            return new ResponseEntity<>("source account does not exist", HttpStatus.FORBIDDEN);

        }
        //Verificar que exista la cuenta de destino//
        if (targetAccount == null)  {

            return new ResponseEntity<>("destination account does not exist", HttpStatus.FORBIDDEN);

        }
        //Verificar que los números de cuenta no sean iguales//
        if (targetAccount.getNumber() == originAccount.getNumber() ) {

            return new ResponseEntity<>("the account number cannot be the same as the origin number", HttpStatus.FORBIDDEN);

        }
        //Verificar que la cuenta de origen pertenezca al cliente autenticado//

        if (!client.getAccounts().contains(originAccount) )  {

            return new ResponseEntity<>("the source account does not belong to a registered customer", HttpStatus.FORBIDDEN);

        }

        //Verificar que la cuenta de origen tenga el monto disponible.//
        if (originAccount.getBalance() < amount)  {

            return new ResponseEntity<>("the account does not have an available amount", HttpStatus.FORBIDDEN);

        }

        Transaction originTransaction = new Transaction(originAccount,DEBITO,-amount,description,LocalDateTime.now());
        transactionService.saveTransaction(originTransaction);
        Transaction targetTransaction = new Transaction(targetAccount,CREDITO,amount,description,LocalDateTime.now());
        transactionService.saveTransaction(targetTransaction);

        originAccount.setBalance(originAccount.getBalance() - amount );
        accountService.saveAccount(originAccount);
        targetAccount.setBalance(targetAccount.getBalance() + amount );
        accountService.saveAccount(targetAccount);

        return new ResponseEntity<>(HttpStatus.CREATED);

           }
}
