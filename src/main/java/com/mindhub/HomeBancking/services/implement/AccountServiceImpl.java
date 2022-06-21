package com.mindhub.HomeBancking.services.implement;

import com.mindhub.HomeBancking.DTO.AccountDTO;
import com.mindhub.HomeBancking.Repositories.AccountRepository;
import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;


    @Override
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(toList());
    }

    @Override
    public AccountDTO getAccounts(Long id) {
        return accountRepository.findById(id).map(account -> new AccountDTO(account)).orElse(null);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findByNumber(String sourceAccount) {
        return accountRepository.findByNumber(sourceAccount);
    }


}
