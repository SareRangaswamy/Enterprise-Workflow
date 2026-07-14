package com.ranga.Enterprise_workflow.repository;

import com.ranga.Enterprise_workflow.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Documentrepository extends JpaRepository<Document, Long> {
}