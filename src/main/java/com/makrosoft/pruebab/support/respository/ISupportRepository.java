package com.makrosoft.pruebab.support.respository;

import com.makrosoft.pruebab.support.model.Support;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISupportRepository extends JpaRepository<Support, Integer> {
    // Método para encontrar el soporte con la mayor prioridad
    Optional<Support> findFirstBySupportAssignatedDateIsNullOrderBySupportPriorityDesc();
}
