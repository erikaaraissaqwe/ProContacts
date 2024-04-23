package com.silvaerika.procontacts.service.professional;

import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.repository.professional.IProfessionalRepository;
import org.springframework.beans.BeanUtils;
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

    /**
     * Salva um novo profissional.
     *
     * @param professional O profissional a ser salvo.
     * @return O profissional salvo.
     */
    @Override
    public Professional save(Professional professional) {
        return professionalRepository.save(professional);
    }

    /**
     * Retorna todos os profissionais.
     *
     * @return Uma lista de todos os profissionais.
     */
    @Override
    public List<Professional> findAll() {
        return professionalRepository.findAll();
    }

    /**
     * Encontra um profissional pelo ID.
     *
     * @param id O ID do profissional.
     * @return Um Optional contendo o profissional, se encontrado.
     */
    @Override
    public Optional<Professional> findById(Long id) {
        return professionalRepository.findById(id);
    }

    /**
     * Atualiza um profissional existente.
     *
     * @param professional O profissional com os novos dados.
     * @return O profissional atualizado.
     * @throws RuntimeException Se o profissional não for encontrado com o ID fornecido.
     */
    @Override
    public Professional update(Professional professional) {
        Professional existingProfessional = professionalRepository.findById(professional.getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado com o ID: " + professional.getId()));
        BeanUtils.copyProperties(professional, existingProfessional, "id");
        return professionalRepository.save(professional);
    }

    /**
     * Exclui um profissional pelo ID.
     *
     * @param id O ID do profissional a ser excluído.
     */
    @Override
    public void deleteById(Long id) {
        professionalRepository.deleteById(id);
    }

    /**
     * Retorna campos dos profissionais com base nos parâmetros fornecidos e filtrados pelo valor dos atributos.
     *
     * @param q      Valor que deverá ser filtrado nos atributos de nome e cargo.
     * @param fields Os campos pelos quais a busca retornará, ex: createdDate, id.
     * @return Uma lista de map contendo os profissionais encontrados somente com os campos escolhidos, se escolhidos.
     */
    @Override
    public List<Map<String, Object>> findByParams(String q, List<String> fields) {
        List<Professional> professionals = findProfessionalByAttribute(q);

        if (fields == null || fields.isEmpty())
            return findByFields(professionals, null);

        return findByFields(professionals, fields);
    }

    /**
     * Encontra profissionais pelo valor dos atributos de nome e cargo.
     *
     * @param value O valor a ser usado na busca.
     * @return Uma lista de contatos encontrados.
     */
    private List<Professional> findProfessionalByAttribute(String value) {
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
