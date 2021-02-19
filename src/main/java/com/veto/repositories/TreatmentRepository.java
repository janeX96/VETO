package com.veto.repositories;

import com.veto.model.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TreatmentRepository {

    List<Treatment> findAll();

    boolean existsById(Integer id);

    Page<Treatment> findAll(Pageable page);

    Optional<Treatment> findById(Integer id);

    Treatment save(Treatment entity);

    void deleteTreatmentById(int id);


   // List<Treatment> findByName(@Param("name")String name);
}
