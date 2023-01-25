package com.kasenov.libpro.simplelibrary;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleLibraryApplication {

    private static ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleLibraryApplication.class, args);
    }

}
