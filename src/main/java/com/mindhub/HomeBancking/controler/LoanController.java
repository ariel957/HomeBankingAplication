package com.mindhub.HomeBancking.controler;

import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.DTO.LoanAplicationDTO;
import com.mindhub.HomeBancking.DTO.LoanDTO;
import com.mindhub.HomeBancking.Repositories.*;
import com.mindhub.HomeBancking.models.*;
import com.mindhub.HomeBancking.services.AccountService;
import com.mindhub.HomeBancking.services.ClientService;
import com.mindhub.HomeBancking.services.LoanService;
import com.mindhub.HomeBancking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBancking.models.TransactionType.CREDITO;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    private LoanService loanService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping(value="/loans")

    public ResponseEntity<String> addLoan(@RequestBody LoanAplicationDTO loanAplicationDTO,Authentication authentication ) {
    Loan loan = loanService.getLoanFindById(loanAplicationDTO.getLoanId());
    Client client = clientService.getClientCurrent(authentication);
    Account account = accountService.findByNumber(loanAplicationDTO.getAccountNumber());

        //Verificar que los parámetros no estén vacíos//
        if (loanAplicationDTO.getAccountNumber().isEmpty() ) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }
        //Verificar que los parámetros no estén vacíos (number)//
        if (loanAplicationDTO.getAmount() == 0 || loanAplicationDTO.getPayment() == 0 ) {

            return new ResponseEntity<>("Input value not allowed", HttpStatus.FORBIDDEN);

        }
        //Verificar que el prestamo exista//
        if (loan == null) {

            return new ResponseEntity<>("the loan does not exist", HttpStatus.FORBIDDEN);

        }
        //Verificar que la cuenta destino exista//
        if (loanAplicationDTO.getAccountNumber() == null) {

            return new ResponseEntity<>("the destination account does not exist", HttpStatus.FORBIDDEN);

        }
        //Verifica que la cantidad de cuotas se encuentre entre las disponibles del préstamo//
        if (!loan.getPayments().contains(loanAplicationDTO.getPayment())) {

            return new ResponseEntity<>("the account does not have an available amount", HttpStatus.FORBIDDEN);

        }

        //Verificar que la cuenta de origen pertenezca al cliente autenticado//

        if (!client.getAccounts().contains(account) )  {

            return new ResponseEntity<>("the source account does not belong to a registered customer", HttpStatus.FORBIDDEN);

        }
        //Se debe crear una solicitud de préstamo con el monto solicitado sumando el 20% del mismo//
        ClientLoan clientLoan=new ClientLoan (loanAplicationDTO.getAmount()*1.20, loanAplicationDTO.getPayment(), client,loan );
        loanService.saveLoan(clientLoan);

        Transaction targetTransaction = new Transaction(account,CREDITO,loanAplicationDTO.getAmount(),"Prestamo"+ " " + loanAplicationDTO.getLoanId() , LocalDateTime.now());
        transactionService.saveTransaction(targetTransaction);

        account.setBalance(account.getBalance() + loanAplicationDTO.getAmount() );
        accountService.saveAccount(account);

        return new ResponseEntity<>("Prestamo aprobado!",HttpStatus.CREATED);
    }
    @GetMapping("/loans")
    public List<LoanDTO> getLoan() {
        return loanService.getLoan();
    }
}
