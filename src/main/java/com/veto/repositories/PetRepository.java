package com.veto.repositories;

import com.veto.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    List<Pet> findAll();

    boolean existsById(Integer id);

    boolean existsByOwnerPhoneNumber(String phone);

    Page<Pet> findAll(Pageable page);

    Optional<Pet> findById(Integer id);

    Pet save(Pet entity);

    List<Pet> findByName(@Param("name")String name);

    void deletePetById(int id);

    boolean existsByName(String name);
}
