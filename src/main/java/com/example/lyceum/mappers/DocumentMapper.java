package com.example.lyceum.mappers;


import com.example.lyceum.anotations.Mapper;
import com.example.lyceum.models.dto.DocumentDto;
import com.example.lyceum.models.enums.DocumentType;
import com.example.lyceum.models.jpa.domain.AuthUser;
import com.example.lyceum.services.body.DocumentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Mapper
@RequiredArgsConstructor
public class DocumentMapper {

    private final DocumentService documentService;

    @Transactional
    public void map(Map<MultipartFile, DocumentType> multipartFiles, AuthUser authUser){
        List<DocumentDto> documents = documentService.multipartFilesToDocuments(multipartFiles);
        documents.forEach(it -> {
            documentService.createDocument(it, authUser.getId());
        });
    }
}
