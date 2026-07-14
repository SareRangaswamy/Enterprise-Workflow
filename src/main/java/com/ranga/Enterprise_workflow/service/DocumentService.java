package com.ranga.Enterprise_workflow.service;

import com.ranga.Enterprise_workflow.entity.Document;
import com.ranga.Enterprise_workflow.repository.Documentrepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final Documentrepository Documentrepository;

    public DocumentService(Documentrepository documentRepository) {
        this.Documentrepository = documentRepository;
    }

    public Document saveDocument(Document document) {
        return Documentrepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return Documentrepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return Documentrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public void deleteDocument(Long id) {
        Documentrepository.deleteById(id);
    }
}