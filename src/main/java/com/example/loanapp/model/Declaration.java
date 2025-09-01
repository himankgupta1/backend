package com.example.loanapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "declarations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Declaration {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private Boolean agreed; // checkbox state

    private String signature; // typed name
    private LocalDate date;   // submission date
}
