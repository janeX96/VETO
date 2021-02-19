package com.veto.sqlRepositories;

import com.veto.model.Vet;
import com.veto.repositories.VetRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SqlVetRepository extends VetRepository, JpaRepository<Vet, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*)>0 from vets where id = :id")
    boolean existsById(@Param("id") Integer id);

//    @Override
//    @Query(nativeQuery = true, value = "select id, firstName, lastName, email from vets")
//    List<Vet> getAll();

    boolean existsByEmail(@Param("email") String email);
    void deleteVetById(@Param("id") Integer id);
}
