package com.veto.event;

import com.veto.model.Pet;
import java.time.Clock;

public class PetPhoneChanged extends PetEvent {
    PetPhoneChanged(final Pet source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
