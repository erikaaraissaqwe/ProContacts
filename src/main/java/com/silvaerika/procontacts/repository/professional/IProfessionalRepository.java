package com.silvaerika.procontacts.repository.professional;

import com.silvaerika.procontacts.model.professional.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProfessionalRepository extends JpaRepository<Professional, Long> {
    @Query("SELECT p FROM Professional p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Professional> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
