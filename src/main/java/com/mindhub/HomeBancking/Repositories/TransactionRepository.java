package com.mindhub.HomeBancking.Repositories;

import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
