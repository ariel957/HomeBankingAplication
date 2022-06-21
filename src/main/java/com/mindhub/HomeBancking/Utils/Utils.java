package com.mindhub.HomeBancking.Utils;

import com.mindhub.HomeBancking.Repositories.AccountRepository;
import com.mindhub.HomeBancking.models.Account;
import com.mindhub.HomeBancking.services.AccountService;

public class Utils {

    public static int generator (int min , int max) {
        return (int)((Math.random() * (max-min)) + min);
    }

    public static int createAccount(AccountService accountService){
        return generator(100,9999);
    }

}
