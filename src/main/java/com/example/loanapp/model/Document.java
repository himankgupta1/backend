package com.example.loanapp.model;

import com.example.loanapp.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Document {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private String originalFilename;
    private String contentType;
    private long sizeBytes;
    private String storagePath; // on disk

    private OffsetDateTime uploadedAt;
}
