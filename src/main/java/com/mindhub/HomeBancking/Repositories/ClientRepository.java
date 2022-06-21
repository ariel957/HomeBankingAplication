package com.mindhub.HomeBancking.Repositories;
import com.mindhub.HomeBancking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByMail(String mail);
}
