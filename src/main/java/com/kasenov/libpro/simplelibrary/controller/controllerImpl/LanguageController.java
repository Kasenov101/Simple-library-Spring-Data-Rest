package com.kasenov.libpro.simplelibrary.controller.controllerImpl;


import com.kasenov.libpro.simplelibrary.controller.AbstractController;
import com.kasenov.libpro.simplelibrary.dto.dtoImpl.Language;
import com.kasenov.libpro.simplelibrary.model.EntityImpl.LanguageEntity;
import com.kasenov.libpro.simplelibrary.repository.LanguageRepository;
import com.kasenov.libpro.simplelibrary.service.ServiceImpl.LanguageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/languages")
public class LanguageController extends AbstractController<LanguageEntity,
        LanguageRepository, LanguageService, Language> {


    public LanguageController(LanguageService service, Language dto, LanguageEntity entity) {
        super(service, dto, entity);
    }
}
