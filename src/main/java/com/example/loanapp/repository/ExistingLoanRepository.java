package com.example.loanapp.repository;

import com.example.loanapp.model.ExistingLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ExistingLoanRepository extends JpaRepository<ExistingLoan, UUID> {}
