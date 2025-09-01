package com.example.loanapp.model;

import com.example.loanapp.model.enums.LoanType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "new_loan_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NewLoanDetails {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LoanType loanType;

    @NotNull
    @Column(precision = 15, scale = 2)
    private BigDecimal loanAmount;

    @NotNull
    private Integer tenureMonths;
}
