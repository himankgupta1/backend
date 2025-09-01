package com.example.loanapp.repository;

import com.example.loanapp.model.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DeclarationRepository extends JpaRepository<Declaration, UUID> {}
