package com.docManagement.system.service;

import com.docManagement.system.model.Document;
import com.docManagement.system.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService {

    private final DocumentRepository repo;

    public DocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    public Document create(Document doc) {
//        doc.setCreatedAt(Instant.now());
        return repo.save(doc);
    }

    public List<Document> listAll() {
        return repo.findAll();
    }

    public Document getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found with id " + id));
    }

    public Document update(Long id, Document doc) {
        Document existing = getById(id);
        existing.setName(doc.getName());
        existing.setContent(doc.getContent());
        existing.setCategory(doc.getCategory());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Total number of documents
    public long totalDocuments() {
        return repo.count();
    }

    // Category-wise count
    public List<Map<String, Object>> categoryWiseCount() {
        List<Object[]> results = repo.countDocumentsByCategory();
        List<Map<String, Object>> response = new java.util.ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("category", row[0]);
            map.put("count", row[1]);
            response.add(map);
        }
        return response;
    }
}