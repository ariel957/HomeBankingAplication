package com.mindhub.HomeBancking.controler;


import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.Repositories.AccountRepository;
import com.mindhub.HomeBancking.Repositories.ClientRepository;
import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.models.Client;
import com.mindhub.HomeBancking.services.AccountService;
import com.mindhub.HomeBancking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBancking.Utils.Utils.createAccount;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
    return clientService.getClientsDto();

}
    @GetMapping("clients/{id}")

    public ClientDTO getClients(@PathVariable Long id){

        return clientService.getClientDto(id);

    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;


    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String mail, @RequestParam String password) {



        if (firstName.isEmpty() || lastName.isEmpty() || mail.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }



        if (clientService.getClientByMail(mail) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }

        Client client= new Client(firstName, lastName, mail, passwordEncoder.encode(password));

        clientService.saveClient(client);

        Account account = new Account(	"VIN"+ createAccount(accountService), LocalDateTime.now(),0.0,client);
        accountRepository.save(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO getClientCurrent(Authentication authentication) {
        return new ClientDTO(clientService.getClientCurrent(authentication));
    }
};

