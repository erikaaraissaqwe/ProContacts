package com.silvaerika.procontacts.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static <T> List<Map<String, Object>> findByFields(List<T> items, List<String> fields) {
        ObjectMapper mapper = new ObjectMapper();
        return items.stream()
                .map(item -> {
                    Map<String, Object> map = mapper.convertValue(item, Map.class);
                    if (fields == null || fields.isEmpty()) {
                        return map;
                    } else {
                        return map.entrySet().stream()
                                .filter(entry -> fields.contains(entry.getKey()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    }
                })
                .collect(Collectors.toList());
    }
}
