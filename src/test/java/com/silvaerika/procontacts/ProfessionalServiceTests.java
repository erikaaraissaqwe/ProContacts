package com.silvaerika.procontacts;

import com.silvaerika.procontacts.model.professional.Professional;
import com.silvaerika.procontacts.repository.professional.IProfessionalRepository;
import com.silvaerika.procontacts.service.professional.ProfessionalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProcontactsApplication.class, properties = {"spring.profiles.active=test"})
public class ProfessionalServiceTests {

    @Autowired
    private ProfessionalServiceImpl professionalService;

    @MockBean
    private IProfessionalRepository professionalRepository;

    @Test
    public void testFindAll() {
        when(professionalRepository.findAll()).thenReturn(Arrays.asList(new Professional(), new Professional()));
        assertEquals(2, professionalService.findAll().size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Professional professional = new Professional();
        professional.setId(id);
        when(professionalRepository.findById(id)).thenReturn(Optional.of(professional));
        assertEquals(professional, professionalService.findById(id).get());
    }

    @Test
    public void testSave() {
        Professional professional = new Professional();
        when(professionalRepository.save(any(Professional.class))).thenReturn(professional);
        assertEquals(professional, professionalService.save(new Professional()));
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(professionalRepository).deleteById(id);
        professionalService.deleteById(id);
        verify(professionalRepository, times(1)).deleteById(id);
    }
}
