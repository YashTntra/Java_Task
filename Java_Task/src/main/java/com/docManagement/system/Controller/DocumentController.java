package com.docManagement.system.Controller;

import com.docManagement.system.model.Document;
import com.docManagement.system.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.core.annotation.MergedAnnotations.search;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @PostMapping
    public Document create(@RequestBody Document doc) {
        return service.create(doc);
    }

    @GetMapping
    public List<Document> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Document getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Document update(@PathVariable Long id, @RequestBody Document doc) {
        return service.update(id, doc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/total")
    public long getTotalDocuments() {
        return service.totalDocuments();
    }

    // Category-wise count
    @GetMapping("/category-count")
    public List<Map<String, Object>> getCategoryWiseCount() {
        return service.categoryWiseCount();
    }

//    @GetMapping("/{id}/versions")
//    public List<Document> getVersions(@PathVariable Long id) {
//        return service.getVersions(id);
//    }

    // Search endpoint
//    @GetMapping("/search")
//    public List<Document> searchDocuments(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String category
//    ) {
//        return service.searchByFilters(name, category);
//    }


    // Search by name
    @GetMapping("/search/name")
    public List<Document> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    // Search by category
    @GetMapping("/search/category")
    public List<Document> searchByCategory(@RequestParam String category) {
        return service.searchByCategory(category);
    }

    // Search by combined filters
    @GetMapping("/search/filters")
    public List<Document> searchByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return service.searchByFilters(name, category);
    }

    @GetMapping("/search")
    public List<Document> searchDocuments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword
    ) {
        return service.searchByFilters(name, category, keyword);
    }


}




