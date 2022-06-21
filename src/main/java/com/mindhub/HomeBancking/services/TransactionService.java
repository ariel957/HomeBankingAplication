package com.mindhub.HomeBancking.services;

import com.mindhub.HomeBancking.DTO.TransactionDTO;
import com.mindhub.HomeBancking.models.Transaction;

import java.util.List;

public interface TransactionService {

    void saveTransaction(Transaction transaction);
}
