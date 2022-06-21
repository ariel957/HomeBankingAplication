package com.mindhub.HomeBancking.DTO;

import com.mindhub.HomeBancking.models.ClientLoan;
import com.mindhub.HomeBancking.models.Loan;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoanDTO {
    private long id;

    private String name;

    private long maxAmount;

    private List<Integer> payments = new ArrayList<>();

    public LoanDTO() {}

    public LoanDTO(Loan loan) {
        this.id= loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.payments = loan.getPayments();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getMaxAmount() {
        return maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }
}
