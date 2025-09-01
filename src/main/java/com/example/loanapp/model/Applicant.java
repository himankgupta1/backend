package com.example.loanapp.model;

import com.example.loanapp.model.enums.Gender;
import com.example.loanapp.model.enums.MaritalStatus;
import com.example.loanapp.model.enums.OccupationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Applicant {

    @Id
    @GeneratedValue
    private UUID id;

    // Personal Details
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;

    @NotBlank
    @Column(length = 15)
    private String phoneNumber;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @NotBlank
    @Column(columnDefinition = "text")
    private String currentAddress;

    @NotBlank
    @Column(columnDefinition = "text")
    private String permanentAddress;

    @NotBlank
    @Column(length = 20)
    private String aadharNum;

    @NotBlank
    @Column(length = 20)
    private String panNum;

    // Employment
    @Enumerated(EnumType.STRING)
    private OccupationType occupationType;

    private String companyName;
    private String designation;
    private Integer workExperience; // years

    @Column(precision = 15, scale = 2)
    private BigDecimal monthlySalary;

    @Column(columnDefinition = "text")
    private String officeAddress;

    // Financials
    @Column(precision = 15, scale = 2)
    private BigDecimal existingLoansEmi;

    @Column(precision = 15, scale = 2)
    private BigDecimal creditCardEmi;

    @Column(precision = 15, scale = 2)
    private BigDecimal otherIncome;

    @NotNull
    @Column(precision = 15, scale = 2)
    private BigDecimal monthlyExpenses;

    // New Loan Details
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private NewLoanDetails newLoanDetails;

    // Existing Loans
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ExistingLoan> existingLoans = new ArrayList<>();

    // References
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReferenceContact> references = new ArrayList<>();

    // Declaration
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Declaration declaration;

    // Documents
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Document> documents = new ArrayList<>();

    // Convenience helpers
    public void addReference(ReferenceContact ref) {
        ref.setApplicant(this);
        this.references.add(ref);
    }

    public void addExistingLoan(ExistingLoan loan) {
        loan.setApplicant(this);
        this.existingLoans.add(loan);
    }

    public void addDocument(Document doc) {
        doc.setApplicant(this);
        this.documents.add(doc);
    }
}
