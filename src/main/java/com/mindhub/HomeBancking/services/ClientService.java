package com.mindhub.HomeBancking.services;

import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClientsDto();
    Client getClientCurrent(Authentication authentication);
    ClientDTO getClientDto(long id);
    void saveClient(Client Client);
    Client getClientByMail(String mail);
}
