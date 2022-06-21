package com.mindhub.HomeBancking.controler;

import com.mindhub.HomeBancking.DTO.AccountDTO;
import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.Repositories.AccountRepository;
import com.mindhub.HomeBancking.Repositories.ClientRepository;
import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.models.Client;
import com.mindhub.HomeBancking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.mindhub.HomeBancking.Utils.Utils.createAccount;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccounts();

    }

    @GetMapping("accounts/{id}")

    public AccountDTO getAccounts(@PathVariable Long id) {

        return accountService.getAccounts(id);

    }

        @Autowired
        private ClientRepository clientRepository;

        @PostMapping(path = "/clients/current/accounts")
        public ResponseEntity<Object> createAccountInCurrent(Authentication authentication) {

            Client client = clientRepository.findByMail(authentication.getName());

            Set<Account> accounts = client.getAccounts();
            if (accounts.size() >= 3) {
                return new ResponseEntity<>("No puedes tener mas de 3 cuentas", HttpStatus.FORBIDDEN);
            }

            Account account = new Account(	"VIN"+ createAccount(accountService), LocalDateTime.now(),0.0,client);
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }





};

