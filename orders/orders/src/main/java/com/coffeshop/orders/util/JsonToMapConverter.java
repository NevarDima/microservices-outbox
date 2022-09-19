package com.coffeshop.orders.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Map<String, Object> payload) {
        return objectMapper.writeValueAsString(payload);
    }

    @SneakyThrows
    @Override
    public Map<String, Object> convertToEntityAttribute(String payloadJSON) {
        return objectMapper.readValue(payloadJSON, Map.class);
    }
}
