package com.docManagement.system.Controller;

import com.docManagement.system.model.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.docManagement.system.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document doc) {
        Document created = service.create(doc);
        return ResponseEntity.created(URI.create("/api/documents/" + created.getId()))
                .body(created);
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<Document> update(@PathVariable Long id, @RequestBody Document doc) {
        Document updated = service.update(id, doc);
        return ResponseEntity.ok(updated);
    }

}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public List<Document> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> get(@PathVariable Long id) {
        Document doc = service.getById(id);
        return ResponseEntity.ok(doc);
    }



    // Total number of documents
    @GetMapping("/total")
    public long getTotalDocuments() {
        return service.totalDocuments();
    }

    // Category-wise count
    @GetMapping("/category-count")
    public List<Map<String, Object>> getCategoryWiseCount() {
        return service.categoryWiseCount();
    }


}
