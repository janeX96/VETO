package com.veto.reports;

import com.veto.model.Pet;
import com.veto.repositories.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final PetRepository petRepository;
    private final PersistedPetEventRepository eventRepository;

    public ReportController(PetRepository petRepository, PersistedPetEventRepository eventRepository) {
        this.petRepository = petRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/count/{id}")
    ResponseEntity<PetWithChangesCount> readPetsWithCount(@PathVariable int id)
    {
        return petRepository
                .findById(id)
                .map(pet -> new PetWithChangesCount(pet, eventRepository.findByPetId(id)))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    private class PetWithChangesCount {
        public String name;
        public String type;
        public String ownerFirstName;
        public String ownerLastName;
        public String ownerPhoneNumber;

        public int changesCount;

        PetWithChangesCount(Pet pet, List<PersistedPetEvent> events) {
            name = pet.getName();
            type = pet.getType();
            ownerFirstName = pet.getOwnerFirstName();
            ownerLastName = pet.getOwnerLastName();
            ownerPhoneNumber = pet.getOwnerPhoneNumber();
            changesCount = events.size();
        }
    }
}
