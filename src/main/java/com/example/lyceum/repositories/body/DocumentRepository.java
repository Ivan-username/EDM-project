package com.example.lyceum.repositories.body;

import com.example.lyceum.models.jpa.body.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findDocumentsByUserId(Long id);
}
