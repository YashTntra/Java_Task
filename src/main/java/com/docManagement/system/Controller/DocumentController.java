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