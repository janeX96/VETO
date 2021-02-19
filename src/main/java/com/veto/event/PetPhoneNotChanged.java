package com.veto.event;

import com.veto.model.Pet;

import java.time.Clock;

public class PetPhoneNotChanged extends PetEvent {
    PetPhoneNotChanged(Pet source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
