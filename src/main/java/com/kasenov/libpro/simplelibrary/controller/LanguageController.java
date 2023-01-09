package com.kasenov.libpro.simplelibrary.controller;


import com.kasenov.libpro.simplelibrary.model.Language;
import com.kasenov.libpro.simplelibrary.repository.LanguageRepository;
import com.kasenov.libpro.simplelibrary.service.LanguageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/languages")
public class LanguageController extends AbstractController<Language,
        LanguageRepository, LanguageService>{

    public LanguageController(LanguageService service) {
        super(service);
    }
}
