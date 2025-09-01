package com.example.loanapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "existing_loans")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ExistingLoan {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @NotBlank
    private String loanType;

    @NotNull
    @Column(precision = 15, scale = 2)
    private BigDecimal amount;

    @NotNull
    private Integer tenureMonths;

    @NotBlank
    private String purpose;
}
