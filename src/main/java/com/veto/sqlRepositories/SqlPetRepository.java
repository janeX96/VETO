package com.veto.sqlRepositories;

import com.veto.model.Pet;
import com.veto.repositories.PetRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SqlPetRepository extends PetRepository, JpaRepository<Pet, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*)>0 from pets where id = :id")
    boolean existsById(@Param("id") Integer id);
    boolean existsByOwnerPhoneNumber(@Param("phone") String phone);
    void deletePetById(@Param("id") Integer id);
}
