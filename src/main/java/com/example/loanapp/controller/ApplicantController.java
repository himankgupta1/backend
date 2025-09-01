package com.example.loanapp.controller;

import com.example.loanapp.dto.ApplicantRequest;
import com.example.loanapp.model.Applicant;
import com.example.loanapp.service.ApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Applicant create(@Valid @RequestBody ApplicantRequest req) {
        return applicantService.create(req);
    }

    @GetMapping("/{id}")
    public Applicant get(@PathVariable UUID id) {
        return applicantService.get(id);
    }

    @PutMapping("/{id}")
    public Applicant update(@PathVariable UUID id, @Valid @RequestBody ApplicantRequest req) {
        return applicantService.update(id, req);
    }

    @GetMapping
    public Page<Applicant> list(Pageable pageable) {
        return applicantService.list(pageable);
    }
}
