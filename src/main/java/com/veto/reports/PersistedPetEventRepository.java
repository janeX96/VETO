package com.veto.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersistedPetEventRepository extends JpaRepository<PersistedPetEvent, Integer> {
    List<PersistedPetEvent> findByPetId(int petId);

}
