package com.mindhub.HomeBancking.services;

import com.mindhub.HomeBancking.DTO.AccountDTO;
import com.mindhub.HomeBancking.models.Account;


import java.util.List;

public interface AccountService {
     List<AccountDTO> getAccounts();

     AccountDTO getAccounts(Long id);

    void saveAccount(Account account);

    Account findByNumber(String sourceAccount);
}
