package ru.chsu.model.dto;

import java.time.LocalDate;

public class UpdateLoan extends RequestLoan {
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
