package ru.chsu.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ReaderDto {
    private Long id;
    private String name;
    private String email;
    private List<LoanDto> loans = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LoanDto> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDto> loans) {
        this.loans = loans;
    }

    public void addLoan(LoanDto loan) {
        this.loans.add(loan);
    }

    public void removeLoan(LoanDto loan) {
        this.loans.remove(loan);
    }
}
