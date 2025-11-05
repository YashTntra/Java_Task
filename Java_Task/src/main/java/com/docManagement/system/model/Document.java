//package com.docManagement.system.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//
//import java.time.Instant;
//import jakarta.persistence.Lob;
//import org.hibernate.annotations.JdbcTypeCode;
//import java.sql.Types;
//
//@Entity
//@Table(name = "documents")
//public class Document {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @Column(length = 2000)
//    private String content;
//
//    private String category;
//
//
//    public Document() {}
//
//    public Document(String name, String content, String category) {
//        this.name = name;
//        this.content = content;
//        this.category = category;
//    }
//
//    // getters and setters
//
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getContent() { return content; }
//    public void setContent(String content) { this.content = content; }
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//}


package com.docManagement.system.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "document_versions")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String content;

    private String category;


    public Document() {
    }

    public Document(String name, String content, String category) {

        this.name = name;
        this.content = content;
        this.category = category;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    //    public Long getDocumentId() { return documentId; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}