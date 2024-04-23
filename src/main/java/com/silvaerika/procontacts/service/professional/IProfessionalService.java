package com.silvaerika.procontacts.service.professional;

import com.silvaerika.procontacts.model.professional.Professional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProfessionalService {
    Professional save(Professional professional);

    List<Professional> findAll();

    Optional<Professional> findById(Long id);

    Professional update(Professional professional);

    void deleteById(Long id);

    List<Map<String, Object>> findByParams(String q, List<String> fields);
}
