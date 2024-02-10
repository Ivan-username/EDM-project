package com.example.lyceum.controllers;


import com.example.lyceum.exceptions.FileDataIsEmptyException;
import com.example.lyceum.models.dto.DocumentDto;
import com.example.lyceum.models.enums.DocumentType;
import com.example.lyceum.models.jpa.domain.AuthUser;
import com.example.lyceum.services.body.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.example.lyceum.models.enums.DocumentType.PARENT_PERSONAL_DATA;
import static com.example.lyceum.models.enums.DocumentType.STATEMENT;

@Controller
@RequestMapping("/project/lyceum-edm")
@RequiredArgsConstructor
public class ProfileController {

    private final DocumentService documentService;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        if (authentication == null) return "login";
        AuthUser authenticationPrincipalEntity = (AuthUser) authentication.getPrincipal();
        return "profile";
    }

    @PostMapping("/profile/update")
    public String profileUpdate(
            Authentication authentication,
            @RequestParam("statement") MultipartFile statement,
            @RequestParam("parent_personal_data") MultipartFile parent_personal_data
    ) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        Map<MultipartFile, DocumentType> multipartFiles = Map.of(
                statement, STATEMENT,
                parent_personal_data, PARENT_PERSONAL_DATA
        );

        try {
            List<DocumentDto> documents = documentService.multipartFilesToDocuments(multipartFiles);

            documents.forEach(it -> {
                documentService.createDocument(it, authUser.getId());
            });
        } catch (FileDataIsEmptyException ex) {
            return "profile";
        }
        return "profile";
    }
}
