package com.docManagement.system.service;

import com.docManagement.system.model.Document;
import com.docManagement.system.repository.DocumentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DocumentService {

    private final DocumentRepository repo;

    public DocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    public Document create(Document doc) {
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

//    // Document Versions
//    public List<Document> getVersions(Long documentId) {
//        return repo.findByDocumentId(documentId);
//    }

    // Search Documents
    public List<Document> searchDocuments(String keyword) {
        return repo.searchDocuments(keyword);
    }

    // Search by name
    public List<Document> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    // Search by category
    public List<Document> searchByCategory(String category) {
        return repo.findByCategory(category);
    }

    // Search by combined filters
    public List<Document> searchByFilters(String name, String category) {
        return repo.searchByFilters(name, category);
    }

    public List<Document> searchByFilters(String name, String category, String keyword) {
        if (name != null && category != null && keyword != null) {
            return repo.findByNameContainingAndCategoryAndContentContaining(name, category, keyword);
        } else if (name != null && category != null) {
            return repo.findByNameContainingAndCategory(name, category);
        } else if (name != null && keyword != null) {
            return repo.findByNameContainingAndContentContaining(name, keyword);
        } else if (category != null && keyword != null) {
            return repo.findByCategoryAndContentContaining(category, keyword);
        } else if (name != null) {
            return repo.findByNameContaining(name);
        } else if (category != null) {
            return repo.findByCategory(category);
        } else if (keyword != null) {
            return repo.findByContentContaining(keyword);
        } else {
            return repo.findAll();
        }
    }




}