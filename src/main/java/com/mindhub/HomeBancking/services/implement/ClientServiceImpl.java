package com.mindhub.HomeBancking.services.implement;

import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.Repositories.ClientRepository;
import com.mindhub.HomeBancking.models.Client;
import com.mindhub.HomeBancking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public List<ClientDTO> getClientsDto(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public Client getClientCurrent(Authentication authentication) {
        return clientRepository.findByMail(authentication.getName());
    }


    @Override
    public ClientDTO getClientDto(long id) {
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getClientByMail(String mail) {
        return clientRepository.findByMail(mail);
    }

}
