package ru.chsu.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ru.chsu.model.entity.Loan;

public class LoanRepository implements PanacheRepository<Loan> {
}
