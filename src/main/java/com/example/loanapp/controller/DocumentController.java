package com.example.loanapp.controller;

import com.example.loanapp.model.Document;
import com.example.loanapp.model.enums.DocumentType;
import com.example.loanapp.service.DocumentStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applicants/{applicantId}/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentStorageService storageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Document upload(@PathVariable UUID applicantId,
                           @RequestParam DocumentType type,
                           @RequestPart("file") MultipartFile file) throws IOException {
        return storageService.store(applicantId, type, file);
    }

    @GetMapping
    public List<Document> list(@PathVariable UUID applicantId) {
        return storageService.list(applicantId);
    }
}
