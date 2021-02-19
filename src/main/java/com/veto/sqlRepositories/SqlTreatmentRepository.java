package com.veto.sqlRepositories;

import com.veto.model.Treatment;
import com.veto.repositories.TreatmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SqlTreatmentRepository extends TreatmentRepository, JpaRepository<Treatment, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*)>0 from vets where id = :id")
    boolean existsById(@Param("id") Integer id);


    void deleteTreatmentById(@Param("id") Integer id);
}
