package com.silvaerika.procontacts.service.professional;

import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.repository.professional.IProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
