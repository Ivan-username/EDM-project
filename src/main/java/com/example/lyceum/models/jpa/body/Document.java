package com.example.lyceum.models.jpa.body;


import com.example.lyceum.models.enums.DocumentType;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "DOCUMENTS")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "ORIGINAL_FILE_NAME")
    private String OriginalFileName;

    @Column(name = "DOCUMENT_TYPE", nullable = false)
    private DocumentType documentType;

    @Lob
    @Column(name = "DATA")
    private byte[] bytes;
}
