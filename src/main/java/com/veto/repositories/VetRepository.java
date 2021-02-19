package com.veto.repositories;

import com.veto.model.Vet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

public interface VetRepository{

    List<Vet> findAll();

 //   List<Vet> getAll();

    boolean existsById(Integer id);

    Page<Vet> findAll(Pageable page);

    Optional<Vet> findById(Integer id);

    boolean existsByEmail(String email);

    Vet save(Vet entity);

    List<Vet> findByLastName(@Param("lastName")String lastName);

    void deleteVetById(int id);

}
