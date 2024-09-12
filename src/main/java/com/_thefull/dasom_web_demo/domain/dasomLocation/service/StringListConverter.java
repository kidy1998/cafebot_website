package com._thefull.dasom_web_demo.domain.dasomLocation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter(autoApply = true)
public class StringListConverter implements AttributeConverter<List<String>,String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<String> convertToEntityAttribute(String s) {
        try {
            if (s==null)
                return null;
            return mapper.readValue(s, List.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        try{
            if (strings==null)
                return null;
            return mapper.writeValueAsString(strings);

        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
