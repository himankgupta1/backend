package com.example.loanapp.dto;

import com.example.loanapp.model.Declaration;
import com.example.loanapp.model.ExistingLoan;
import com.example.loanapp.model.NewLoanDetails;
import com.example.loanapp.model.ReferenceContact;
import com.example.loanapp.model.enums.Gender;
import com.example.loanapp.model.enums.MaritalStatus;
import com.example.loanapp.model.enums.OccupationType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApplicantRequest {

    // Personal
    @NotBlank private String firstName;
    private String middleName;
    @NotBlank private String lastName;
    @NotBlank private String phoneNumber;
    @Email @NotBlank private String email;
    @NotNull private LocalDate dob;
    private Gender gender;
    private MaritalStatus maritalStatus;
    @NotBlank private String currentAddress;
    @NotBlank private String permanentAddress;
    @NotBlank private String aadharNum;
    @NotBlank private String panNum;

    // Employment
    private OccupationType occupationType;
    private String companyName;
    private String designation;
    private Integer workExperience;
    private BigDecimal monthlySalary;
    private String officeAddress;

    // Financials
    private BigDecimal existingLoansEmi;
    private BigDecimal creditCardEmi;
    private BigDecimal otherIncome;
    @NotNull private BigDecimal monthlyExpenses;

    // Nested
    private NewLoanDetails newLoanDetails;
    @Builder.Default
    private List<ExistingLoan> existingLoans = new ArrayList<>();
    @Builder.Default
    private List<ReferenceContact> references = new ArrayList<>();
    private Declaration declaration;
}
