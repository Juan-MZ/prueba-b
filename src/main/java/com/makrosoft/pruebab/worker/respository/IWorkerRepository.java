package com.makrosoft.pruebab.worker.respository;

import com.makrosoft.pruebab.worker.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWorkerRepository extends JpaRepository<Worker,Integer> {
}
