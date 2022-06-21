package com.mindhub.HomeBancking.DTO;

import com.mindhub.HomeBancking.models.ClientLoan;
import com.mindhub.HomeBancking.models.Transaction;

public class LoanAplicationDTO {

    private long loanId;
    private double amount;
    private int payment;
    private String accountNumber;

    public LoanAplicationDTO() {
    }

    public LoanAplicationDTO(long loanId, double amount, int payment, String accountNumber) {
        this.loanId= loanId;
        this.amount = amount;
        this.payment = payment;
        this.accountNumber = accountNumber;
    }

    public long getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
