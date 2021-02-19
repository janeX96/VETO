package com.veto.event;

import com.veto.model.Pet;

import java.time.Clock;
import java.time.Instant;

public abstract class PetEvent {
    public static PetEvent changed(Pet source, boolean phoneChanged){
        return phoneChanged ? new PetPhoneChanged(source) : new PetPhoneNotChanged(source);
    }
    private int petId;
    private Instant occurrence;

    public PetEvent(int petId, Clock clock) {
        this.petId = petId;
        occurrence = Instant.now(clock);
    }

    public int getPetId() {
        return petId;
    }

    public Instant getOccurrence() {
        return occurrence;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "petId= " + petId +
                ", occurrence= " + occurrence +
                "}";
    }
}
