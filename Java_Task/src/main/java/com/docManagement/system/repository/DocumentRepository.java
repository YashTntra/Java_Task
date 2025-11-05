package com.docManagement.system.repository;

import com.docManagement.system.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
//    List<Document> findByDocumentId(Long documentId);

    @Query("SELECT d.category as category, COUNT(d) as count FROM Document d GROUP BY d.category")
    List<Object[]> countDocumentsByCategory();

    @Query("SELECT d FROM Document d WHERE " +
            "LOWER(d.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(d.content) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(d.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Document> searchDocuments(@Param("keyword") String keyword);

    // Search by name containing text
    List<Document> findByNameContainingIgnoreCase(String name);

    // Search by category
    List<Document> findByCategory(String category);

    // Search by name AND category
    @Query("SELECT d FROM Document d WHERE " +
            "(:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR d.category = :category)")
    List<Document> searchByFilters(@Param("name") String name,
                                   @Param("category") String category);




    List<Document> findByNameContaining(String name);
    List<Document> findByContentContaining(String keyword);
    List<Document> findByNameContainingAndCategory(String name, String category);
    List<Document> findByNameContainingAndContentContaining(String name, String keyword);
    List<Document> findByCategoryAndContentContaining(String category, String keyword);

    List<Document> findByNameContainingAndCategoryAndContentContaining(String name, String category, String keyword);

}
