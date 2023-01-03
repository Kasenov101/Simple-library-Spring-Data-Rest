package com.kasenov.libpro.simplelibrary.Service;

import com.kasenov.libpro.simplelibrary.Entity.Language;
import com.kasenov.libpro.simplelibrary.Repository.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends AbstractService<Language, LanguageRepository>{
    protected LanguageService(LanguageRepository repository) {
        super(repository);
    }
}
