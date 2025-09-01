package com.example.loanapp.service;

import com.example.loanapp.model.Applicant;
import com.example.loanapp.model.Document;
import com.example.loanapp.model.enums.DocumentType;
import com.example.loanapp.repository.ApplicantRepository;
import com.example.loanapp.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentStorageService {

    @Value("${file.storage.location:uploads}")
    private String storageBase;

    private final DocumentRepository documentRepository;
    private final ApplicantRepository applicantRepository;

    @Transactional
    public Document store(UUID applicantId, DocumentType type, MultipartFile file) throws IOException {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("Applicant not found"));

        Path base = Paths.get(storageBase, applicantId.toString());
        Files.createDirectories(base);

        String safeName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path target = base.resolve(safeName);
        Files.copy(file.getInputStream(), target);

        Document doc = Document.builder()
                .applicant(applicant)
                .type(type)
                .originalFilename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .sizeBytes(file.getSize())
                .storagePath(target.toString())
                .uploadedAt(OffsetDateTime.now())
                .build();

        applicant.addDocument(doc);
        return documentRepository.save(doc);
    }

    @Transactional(readOnly = true)
    public List<Document> list(UUID applicantId) {
        return documentRepository.findByApplicantId(applicantId);
    }
}
