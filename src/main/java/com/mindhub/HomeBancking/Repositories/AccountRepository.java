package com.mindhub.HomeBancking.Repositories;

import com.mindhub.HomeBancking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findById(long id);

    Account findByNumber(String sourceAccount);
}
