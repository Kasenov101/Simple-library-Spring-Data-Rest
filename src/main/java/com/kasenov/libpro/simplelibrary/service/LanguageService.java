package com.kasenov.libpro.simplelibrary.service;

import com.kasenov.libpro.simplelibrary.model.Language;
import com.kasenov.libpro.simplelibrary.repository.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends AbstractService<Language, LanguageRepository>{
    protected LanguageService(LanguageRepository repository) {
        super(repository);
    }
}
