package com.example.loanapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "reference_contacts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReferenceContact {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @NotBlank
    private String fullName;

    @NotBlank
    @Column(length = 15)
    private String phone;

    @NotBlank
    private String relationship;
}
