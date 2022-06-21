package com.mindhub.HomeBancking.services;

import com.mindhub.HomeBancking.DTO.ClientDTO;
import com.mindhub.HomeBancking.DTO.LoanDTO;
import com.mindhub.HomeBancking.models.ClientLoan;
import com.mindhub.HomeBancking.models.Loan;

import java.util.List;

public interface LoanService {
    Loan getLoanFindById(long id);
    List<LoanDTO> getLoan();
    void saveLoan(ClientLoan clientLoan);
}
