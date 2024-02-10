package com.example.lyceum.models.dto;


import com.example.lyceum.models.enums.DocumentType;
import com.example.lyceum.models.jpa.body.User;
import lombok.Data;

@Data
public class DocumentDto {
    private Long id;
    private User user;
    private DocumentType documentType;
    private String OriginalFileName;
    private byte[] bytes;
}