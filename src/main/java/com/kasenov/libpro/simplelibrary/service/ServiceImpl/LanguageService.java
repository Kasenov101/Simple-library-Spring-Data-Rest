package com.kasenov.libpro.simplelibrary.service.ServiceImpl;

import com.kasenov.libpro.simplelibrary.model.EntityImpl.LanguageEntity;
import com.kasenov.libpro.simplelibrary.repository.LanguageRepository;
import com.kasenov.libpro.simplelibrary.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends AbstractService<LanguageEntity, LanguageRepository> {

    protected LanguageService(LanguageRepository repository) {
        super(repository);
    }
}
