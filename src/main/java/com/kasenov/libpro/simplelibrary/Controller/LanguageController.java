package com.kasenov.libpro.simplelibrary.Controller;


import com.kasenov.libpro.simplelibrary.Entity.Language;
import com.kasenov.libpro.simplelibrary.Repository.LanguageRepository;
import com.kasenov.libpro.simplelibrary.Service.LanguageService;
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
