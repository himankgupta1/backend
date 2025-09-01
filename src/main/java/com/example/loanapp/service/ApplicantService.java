package com.example.loanapp.service;

import com.example.loanapp.dto.ApplicantRequest;
import com.example.loanapp.model.Applicant;
import com.example.loanapp.model.ExistingLoan;
import com.example.loanapp.model.ReferenceContact;
import com.example.loanapp.repository.ApplicantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public Applicant create(ApplicantRequest req) {
        Applicant a = Applicant.builder()
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .phoneNumber(req.getPhoneNumber())
                .email(req.getEmail())
                .dob(req.getDob())
                .gender(req.getGender())
                .maritalStatus(req.getMaritalStatus())
                .currentAddress(req.getCurrentAddress())
                .permanentAddress(req.getPermanentAddress())
                .aadharNum(req.getAadharNum())
                .panNum(req.getPanNum())
                .occupationType(req.getOccupationType())
                .companyName(req.getCompanyName())
                .designation(req.getDesignation())
                .workExperience(req.getWorkExperience())
                .monthlySalary(req.getMonthlySalary())
                .officeAddress(req.getOfficeAddress())
                .existingLoansEmi(req.getExistingLoansEmi())
                .creditCardEmi(req.getCreditCardEmi())
                .otherIncome(req.getOtherIncome())
                .monthlyExpenses(req.getMonthlyExpenses())
                .newLoanDetails(req.getNewLoanDetails())
                .declaration(req.getDeclaration())
                .build();

        // Attach references & existing loans with back-links
        if (req.getReferences() != null) {
            for (ReferenceContact r : req.getReferences()) {
                r.setApplicant(a);
                a.getReferences().add(r);
            }
        }
        if (req.getExistingLoans() != null) {
            for (ExistingLoan l : req.getExistingLoans()) {
                l.setApplicant(a);
                a.getExistingLoans().add(l);
            }
        }

        return applicantRepository.save(a);
    }

    @Transactional(readOnly = true)
    public Applicant get(UUID id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found"));
    }

    @Transactional
    public Applicant update(UUID id, ApplicantRequest req) {
        Applicant a = get(id);
        // Update basic fields (simple approach; refine as needed)
        a.setFirstName(req.getFirstName());
        a.setMiddleName(req.getMiddleName());
        a.setLastName(req.getLastName());
        a.setPhoneNumber(req.getPhoneNumber());
        a.setEmail(req.getEmail());
        a.setDob(req.getDob());
        a.setGender(req.getGender());
        a.setMaritalStatus(req.getMaritalStatus());
        a.setCurrentAddress(req.getCurrentAddress());
        a.setPermanentAddress(req.getPermanentAddress());
        a.setAadharNum(req.getAadharNum());
        a.setPanNum(req.getPanNum());
        a.setOccupationType(req.getOccupationType());
        a.setCompanyName(req.getCompanyName());
        a.setDesignation(req.getDesignation());
        a.setWorkExperience(req.getWorkExperience());
        a.setMonthlySalary(req.getMonthlySalary());
        a.setOfficeAddress(req.getOfficeAddress());
        a.setExistingLoansEmi(req.getExistingLoansEmi());
        a.setCreditCardEmi(req.getCreditCardEmi());
        a.setOtherIncome(req.getOtherIncome());
        a.setMonthlyExpenses(req.getMonthlyExpenses());
        a.setNewLoanDetails(req.getNewLoanDetails());
        a.setDeclaration(req.getDeclaration());

        // Replace references and existing loans (simple approach)
        a.getReferences().clear();
        if (req.getReferences() != null) {
            for (ReferenceContact r : req.getReferences()) {
                r.setApplicant(a);
                a.getReferences().add(r);
            }
        }
        a.getExistingLoans().clear();
        if (req.getExistingLoans() != null) {
            for (ExistingLoan l : req.getExistingLoans()) {
                l.setApplicant(a);
                a.getExistingLoans().add(l);
            }
        }

        return a; // due to transactional context, will be flushed
    }

    @Transactional(readOnly = true)
    public Page<Applicant> list(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }
}
