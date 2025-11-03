package com.docManagement.system.repository;

import com.docManagement.system.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT d.category as category, COUNT(d) as count FROM Document d GROUP BY d.category")
    List<Object[]> countDocumentsByCategory();

//    boolean existsByDocId(String docId);
//
//    @Query("SELECT d.category AS category, COUNT(d) AS count FROM Document d GROUP BY d.docCategory")
//    List<Object[]> countDocumentsByCategory();
//
//    @Query("SELECT COUNT(d) FROM Document d WHERE d.fileData IS NOT NULL")
//    Long countUploadedDocuments();
}
