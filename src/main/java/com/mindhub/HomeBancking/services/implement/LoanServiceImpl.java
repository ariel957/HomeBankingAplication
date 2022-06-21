package com.mindhub.HomeBancking.services.implement;

import com.mindhub.HomeBancking.DTO.LoanDTO;
import com.mindhub.HomeBancking.Repositories.ClientLoanRepository;
import com.mindhub.HomeBancking.Repositories.LoanRepository;
import com.mindhub.HomeBancking.models.ClientLoan;
import com.mindhub.HomeBancking.models.Loan;
import com.mindhub.HomeBancking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private ClientLoanRepository clientLoanRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Override
    public Loan getLoanFindById(long id) {

        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public List<LoanDTO> getLoan() {
        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(toList());
    }

    @Override
    public void saveLoan(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }

}
