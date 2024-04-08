package com.makrosoft.pruebab.support.respository;

import com.makrosoft.pruebab.support.model.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupportRepository extends JpaRepository<Support, Integer> {
}
