package com.kasenov.libpro.simplelibrary.controller;


import com.kasenov.libpro.simplelibrary.dto.Language;
import com.kasenov.libpro.simplelibrary.model.LanguageEntity;
import com.kasenov.libpro.simplelibrary.repository.LanguageRepository;
import com.kasenov.libpro.simplelibrary.service.LanguageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/languages")
public class LanguageController extends AbstractController<LanguageEntity,
        LanguageRepository, LanguageService, Language>{


    public LanguageController(LanguageService service, Language dto, LanguageEntity entity) {
        super(service, dto, entity);
    }
}
