package com.silvaerika.procontacts.service.professional;

import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.repository.professional.IProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.silvaerika.procontacts.service.utils.Utils.findByFields;

@Service
public class ProfessionalServiceImpl implements IProfessionalService {

    @Autowired
    private IProfessionalRepository professionalRepository;
    @Override
    public Professional save(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Override
    public List<Professional> findAll() {
        return professionalRepository.findAll();
    }

    @Override
    public Optional<Professional> findById(Long id) {
        return professionalRepository.findById(id);
    }

    @Override
    public Professional update(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Override
    public void deleteById(Long id) {
        professionalRepository.deleteById(id);
    }

    @Override
    public List<Map<String, Object>> findByParams(String q, List<String> fields) {
        List<Professional> professionals = findProfessionalByAttribute(q);

        if (fields == null || fields.isEmpty())
            return findByFields(professionals, null);

        return findByFields(professionals, fields);
    }

    public List<Professional> findProfessionalByAttribute(String value) {
        List<Professional> allProfessional = professionalRepository.findAll();

        if(value == null || value.isEmpty())
            return allProfessional;

        return allProfessional.stream()
                .filter(professional ->
                        professional.getName().contains(value) ||
                                professional.getProfessionalPosition().getValue().contains(value))
                .collect(Collectors.toList());
    }

}
