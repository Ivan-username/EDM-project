package com.example.lyceum.services.body;


import com.example.lyceum.exceptions.FileDataIsEmptyException;
import com.example.lyceum.models.dto.DocumentDto;
import com.example.lyceum.models.enums.DocumentType;
import com.example.lyceum.models.jpa.body.Document;
import com.example.lyceum.repositories.body.DocumentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;

    @Transactional
    public void createDocument(DocumentDto documentDto, Long id) {
        Document document = new Document();
        document.setUser(userService.getUserById(id));
        document.setOriginalFileName(documentDto.getOriginalFileName());
        document.setDocumentType(documentDto.getDocumentType());
        document.setBytes(documentDto.getBytes());

        documentRepository.save(document);
    }

    public List<DocumentDto> multipartFilesToDocuments(Map<MultipartFile, DocumentType> multipartFiles) {
        List<DocumentDto> documents = new ArrayList<>();
        multipartFiles.forEach((multipartFile, documentType) -> {
                    DocumentDto documentDto = new DocumentDto();
                    try {
                        documentDto.setBytes(multipartFile.getBytes());
                    } catch (IOException ex) {
                        throw new FileDataIsEmptyException("File with originalFileName: " + multipartFile.getOriginalFilename() + "is empty", ex);
                    }
                    documentDto.setOriginalFileName(multipartFile.getOriginalFilename());
                    documentDto.setDocumentType(documentType);
                    documents.add(documentDto);
                }
        );

        return documents;
    }


}
