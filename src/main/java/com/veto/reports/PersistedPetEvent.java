package com.veto.reports;

import com.veto.event.PetEvent;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "pet_events")
public class PersistedPetEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private LocalDateTime occurrence;
    private int petId;
    private String name;

    public PersistedPetEvent() {
    }

    public PersistedPetEvent(PetEvent source) {
        petId = source.getPetId();
        name = source.getClass().getSimpleName();
        occurrence = LocalDateTime.ofInstant(source.getOccurrence(), ZoneId.systemDefault());

    }
}
