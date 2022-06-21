package com.mindhub.HomeBancking.Repositories;

import com.mindhub.HomeBancking.models.Card;
import com.mindhub.HomeBancking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

   // Client findByMail(String mail); //

}
