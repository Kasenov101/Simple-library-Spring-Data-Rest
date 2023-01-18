package com.kasenov.libpro.simplelibrary.dto.deserializationConvertor;

import com.fasterxml.jackson.databind.util.StdConverter;

public class StringInitCap extends StdConverter<String,String> {
    @Override
    public String convert(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
